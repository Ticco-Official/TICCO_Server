package com.ticco.domain.user;

import com.ticco.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SocialInfo socialInfo;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "onboarding_id", nullable = false)
    private Onboarding onboarding;

    private User(String socialId, UserSocialType socialType, Onboarding onboarding) {
        this.socialInfo = SocialInfo.of(socialId, socialType);
        this.onboarding = onboarding;
        this.status = UserStatus.ACTIVE;
    }

    public static User newInstance(String socialId, UserSocialType socialType, Onboarding onboarding) {
        return new User(socialId, socialType, onboarding);
    }
}
