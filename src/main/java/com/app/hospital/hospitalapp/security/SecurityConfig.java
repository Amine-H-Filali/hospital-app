package com.app.hospital.hospitalapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
       String encode= passwordEncoder.encode("1234");

        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(encode)
                        .roles("USER").build(),

                User.withUsername("user2").password(encode)
                        .roles("USER").build(),

                User.withUsername("admin").password(encode)
                        .roles("USER","ADMIN").build()


        );
    }

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.
        formLogin(form -> form
            .loginPage("/login")
            .permitAll()
                .successHandler((request, response, authentication) -> {
                    // Redirection en fonction des rôles
                    String redirectUrl = "/login";
                    if(authentication.isAuthenticated()) redirectUrl="/user/index";
                    response.sendRedirect(redirectUrl);
                })
    )
                .rememberMe(Customizer.withDefaults())
                .authorizeHttpRequests(ar->ar.requestMatchers("/css/**", "/output.css", "/js/**", "/images/**", "/webjars/**").permitAll())
                .authorizeHttpRequests(ar->ar.requestMatchers("/user/**").hasRole("USER"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())

                .exceptionHandling(exception -> exception
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.sendRedirect("/notAuthorized");
                        })
                )


                .build();

    }
}
