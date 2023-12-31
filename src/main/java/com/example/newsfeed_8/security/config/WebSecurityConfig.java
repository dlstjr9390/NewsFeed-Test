package com.example.newsfeed_8.security.config;

import com.example.newsfeed_8.jwt.filter.JwtAuthorizationFilter;
import com.example.newsfeed_8.jwt.Util.JwtUtil;
import com.example.newsfeed_8.security.service.MemberDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final MemberDetailsService memberDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, objectMapper, memberDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());

        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                        .requestMatchers(HttpMethod.POST,"/api/members/signup").permitAll()
//                        .requestMatchers("/api/members/login").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/api/posts/{postId}").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/api/posts").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/api/posts/{postId}/comments").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/members/logout").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/members").authenticated()
                        .requestMatchers(HttpMethod.PATCH,"/api/members/email").authenticated()
                        .requestMatchers(HttpMethod.PATCH,"/api/members/password").authenticated()
                        .requestMatchers(HttpMethod.PATCH,"/api/members/introduction").authenticated()
                        .anyRequest().permitAll()
        );

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(config -> {
            config.authenticationEntryPoint(errorPoint());
            config.accessDeniedHandler(accessDeniedHandler());
        });

        return http.build();
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, ex) -> {
            response.setStatus(400);
        };
    }

    private AuthenticationEntryPoint errorPoint() {
        return (request, response, authException) -> {
            authException.printStackTrace();
        };
    }
}
