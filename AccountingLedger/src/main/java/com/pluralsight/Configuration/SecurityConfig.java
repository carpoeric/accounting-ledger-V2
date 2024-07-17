package com.pluralsight.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilter(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((requests)-> requests.anyRequest().authenticated());
        http.httpBasic(withDefaults());
        http.sessionManagement(
                session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        );

        return http.build();
        // no need for httpFormLogin, excluded.
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin  = User.withUsername("admin")
                .password("{noop}test")
                .roles("ADMIN")
                .build();

        UserDetails user1  = User.withUsername("testUser")
                .password("{noop}test1")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, admin);
        /* Created two users, one admin and one regular,
        configured csrf to accept all requests,
        allowed any request to be authenticated,
        allowed basic http authentication
        , created stateless api session.
        adm
        */


    }

}
