package com.ticco.service.ticket.dto.response;

import com.ticco.domain.ticket.Ticket;
import com.ticco.domain.ticket.TicketCategory;
import com.ticco.domain.ticket.TicketTheme;
import lombok.*;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketInfoResponse {

    private Long ticketId;

    private String date;

    private TicketCategory category;

    private String title;

    private double rating;

    private String content;

    private String ticketImageUrl;

    private TicketTheme theme;

    @Builder(access = AccessLevel.PACKAGE)
    public TicketInfoResponse(Long ticketId, String date, TicketCategory category, String title, double rating, String content, String ticketImageUrl, TicketTheme theme) {
        this.ticketId = ticketId;
        this.date = date;
        this.category = category;
        this.title = title;
        this.rating = rating;
        this.content = content;
        this.ticketImageUrl = ticketImageUrl;
        this.theme = theme;
    }

    public static TicketInfoResponse of(@NotNull Ticket ticket) {
        return TicketInfoResponse.builder()
                .ticketId(ticket.getId())
                .date(ticket.getDate().toString())
                .category(ticket.getCategory())
                .title(ticket.getTitle())
                .rating(ticket.getRating())
                .content(ticket.getContent())
                .ticketImageUrl(ticket.getTicketImageUrl())
                .theme(ticket.getTheme())
                .build();
    }
}
