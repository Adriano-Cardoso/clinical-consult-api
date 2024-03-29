package com.adriano.clinical.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.httpBasic().disable().csrf().disable().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().headers()
                    .frameOptions().disable().and().authorizeRequests().anyRequest().permitAll();
        } else {
            http.httpBasic().disable().csrf().disable().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().headers()
                    .frameOptions().disable().and().authorizeRequests().antMatchers("/swagger.html").permitAll().and()
                    .authorizeRequests().antMatchers("/swagger-ui.html").permitAll().and()
                    .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().and()
                    .authorizeRequests().antMatchers("/auth").permitAll().and().authorizeRequests()
                    .antMatchers("/patient/**", "/doctor/**", "/consultation/**" +
                            "").authenticated().and()
                    .apply(new JwtConfigure(jwtTokenProvider));

            http.cors().and().csrf().disable();
            http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenProvider, userDetailsService));

        }
    }
}

