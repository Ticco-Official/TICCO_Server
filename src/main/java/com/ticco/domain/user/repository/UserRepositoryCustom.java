package com.ticco.domain.user.repository;

import com.ticco.domain.user.User;
import com.ticco.domain.user.UserSocialType;

public interface UserRepositoryCustom {

    boolean existsBySocialIdAndSocialType(String socialId, UserSocialType socialType);

    User findUserById(Long id);

    User findUserBySocialIdAndSocialType(String socialId, UserSocialType socialType);
}
