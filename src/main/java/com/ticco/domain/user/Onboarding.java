package com.ticco.domain.user;

import com.ticco.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
