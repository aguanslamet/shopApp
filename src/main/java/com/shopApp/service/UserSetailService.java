package com.shopApp.service;

import com.shopApp.dto.response.UserDetailsDto;
import com.shopApp.model.Users;
import com.shopApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserSetailService implements UserDetailsService {
        @Autowired
        UserRepository userRepository;
        @Transactional
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Users user = (Users) userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));

            return UserDetailsDto.build(user);
        }
}
