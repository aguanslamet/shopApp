package com.shopApp.dto.response;

import com.shopApp.model.Users;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Setter
@Getter
@EqualsAndHashCode
public class UserDetailsDto implements UserDetails {
    private Long id;
    private String username;
    private String email;
    private String password;

    public UserDetailsDto(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        public static UserDetailsDto build(Users user) {

            return new UserDetailsDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword());
        }
}
