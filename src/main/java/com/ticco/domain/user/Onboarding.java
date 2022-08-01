package com.ticco.domain.user;

import com.ticco.domain.common.AuditingTimeEntity;
import com.ticco.domain.ticket.Ticket;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Onboarding extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String nickname;

    @Column(length = 300)
    private String profileImageUrl;

    @Column(nullable = false)
    private boolean isChecked;

    @OneToMany(mappedBy = "onboarding", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Ticket> tickets = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    public Onboarding(String nickname, String profileImageUrl, boolean isChecked) {
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.isChecked = isChecked;
    }

    public static Onboarding newInstance(String nickname, String profileImageUrl) {
        return Onboarding.builder()
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .isChecked(false)
                .build();
    }
}
