package com.vibhuti.recipeSharing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails adminUser = User.withUsername("adminUser").password(passwordEncoder().encode("password"))
                .roles("ADMIN","AUTHORIZED").build();
        UserDetails authorizedUser = User.withUsername("authUser").password(passwordEncoder().encode("password"))
                .roles("AUTHORIZED").build();

        return new InMemoryUserDetailsManager(adminUser,authorizedUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                                .requestMatchers("/api/v1/recipe-intake").hasRole("AUTHORIZED")
                                .requestMatchers("/api/v1/recipe/**").permitAll()
                                .requestMatchers("/api/v1/available-recipes").permitAll()
                                .requestMatchers("/api/v1/request-recipe/**").permitAll()
                                .anyRequest().authenticated())
                .formLogin(withDefaults());

        return httpSecurity.build();
    }
}
