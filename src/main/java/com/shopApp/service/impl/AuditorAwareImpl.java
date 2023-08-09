package com.shopApp.service.impl;

import com.shopApp.dto.response.UserDetailsDto;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication.getPrincipal() == "anonymousUser") {
            return Optional.empty();
        }

        UserDetailsDto profile = (UserDetailsDto) authentication.getPrincipal();
        return Optional.ofNullable(profile.getId());

    }

}
