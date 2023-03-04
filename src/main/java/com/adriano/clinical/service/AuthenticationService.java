package com.adriano.clinical.service;

import com.adriano.clinical.exception.Message;
import com.adriano.clinical.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Slf4j
@Validated
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("method=loadUserByUsername login={}", username);
        return this.userRepository.findByLogin(username)
                .orElseThrow(Message.NOT_FOT_USER_PERMISSION::asBusinessException);
    }
}
