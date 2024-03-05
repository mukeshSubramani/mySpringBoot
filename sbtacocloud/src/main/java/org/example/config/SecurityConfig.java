package org.example.config;

import lombok.extern.log4j.Log4j2;
import org.example.domains.User;
import org.example.repo.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Objects;

@Configuration
@Log4j2
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        log.info("Inside passworkEncoder bean");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepo userRepo){
        log.info("Inside UserDetails bean" );
        return username -> {
            log.info("print username {}" , username);
            User user = userRepo.findByUsername(username);
            if(Objects.nonNull(user)){
                return user;
            }
            throw  new UsernameNotFoundException("User  ' {"+ username +"}  not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Inside SecurityFilterChain bean");
        return httpSecurity
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/design","/orders").hasRole("USER")
                                .requestMatchers("/","/**").permitAll())
                .formLogin(httpSecurityFormLoginConfigurer ->
                 httpSecurityFormLoginConfigurer.loginPage("/login"))
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer.logoutSuccessUrl("/"))
                .build();
    }
}
