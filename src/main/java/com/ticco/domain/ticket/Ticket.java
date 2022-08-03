package com.ticco.domain.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ticco.domain.common.AuditingTimeEntity;
import com.ticco.domain.user.Onboarding;
import com.ticco.service.ticket.dto.request.UpdateTicketRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onboarding_id", nullable = false)
    private Onboarding onboarding;

    @Column(nullable = false, length = 100)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Asia/Seoul")
    private LocalDate date;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TicketCategory category;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false, length = 300)
    private String content;

    @Column(nullable = false, length = 300)
    private String ticketImageUrl;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TicketTheme theme;

    @Builder(access = AccessLevel.PACKAGE)
    public Ticket(Onboarding onboarding, LocalDate date, TicketCategory category, String title, double rating, String content, String ticketImageUrl, TicketTheme theme) {
        this.onboarding = onboarding;
        this.date = date;
        this.category = category;
        this.title = title;
        this.rating = rating;
        this.content = content;
        this.ticketImageUrl = ticketImageUrl;
        this.theme = theme;
    }

    public static Ticket newInstance(Onboarding onboarding, LocalDate date, TicketCategory category, String title, double rating, String content, String ticketImageUrl, TicketTheme theme) {
        return Ticket.builder()
                .onboarding(onboarding)
                .date(date)
                .category(category)
                .title(title)
                .rating(rating)
                .content(content)
                .ticketImageUrl(ticketImageUrl)
                .theme(theme)
                .build();
    }

    public void updateInfo(UpdateTicketRequestDto request) {
        this.date = request.getDate();
        this.category = request.getCategory();
        this.title = request.getTitle();
        this.rating = request.getRating();
        this.content = request.getContent();
        this.theme = request.getTheme();
    }

    public void updateImage(String ticketImageUrl) {
        this.ticketImageUrl = ticketImageUrl;
    }
}
