package com.adriano.clinical.service;

import com.adriano.clinical.domain.Profile;
import com.adriano.clinical.domain.User;
import com.adriano.clinical.domain.dto.request.UserRequest;
import com.adriano.clinical.domain.dto.response.UserResponse;
import com.adriano.clinical.exception.Message;
import com.adriano.clinical.repository.ProfileRepository;
import com.adriano.clinical.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Validated
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("method=loadUserByUsername login={}", username);
        return this.userRepository.findByEmail(username)
                .orElseThrow(Message.NOT_FOT_USER_PERMISSION::asBusinessException);
    }

    public UserResponse save(@Valid UserRequest userRequest) {

        this.userRepository.findByEmail(userRequest.getEmail()).ifPresent(p -> {
            throw Message.IS_PRESENT_USER.asBusinessException();
        });

        Profile profile = profileRepository.findByName("USER")
                .orElseThrow(Message.NAME_PROFILE_NOT_FOUND::asBusinessException);

        List<Profile> listProfile = new ArrayList<>();

        listProfile.add(profile);

        User user = User.builder().email(userRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(userRequest.getPassword())).userName(userRequest.getUsername())
                .build();


        user.setProfile(listProfile);

        userRepository.save(user);

        log.info("method=save username={} email={}", userRequest.getUsername(), userRequest.getEmail());

        return user.toResponse();
    }
}
