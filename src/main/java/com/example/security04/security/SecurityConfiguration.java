package com.example.security04.security;

import com.example.security04.util.Constante;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfiguration(JwtTokenProvider jTokenProvider) {
        this.jwtTokenProvider = jTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(x -> x.disable());

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // setting stateless session, because we choose to implement Rest API
        http.sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeRequests(x -> x
                .requestMatchers("/").permitAll()
                .requestMatchers("/api/v1/usuarios/autenticar").permitAll()
                .requestMatchers("/api/v1/usuarios/listar").hasAuthority(Constante.ROL_ADMIN)
                .requestMatchers("/api/v1/usuarios/registrar").hasAuthority(Constante.ROL_ADMIN)
                .requestMatchers("/api/v1/usuarios/validar").hasAuthority(Constante.ROL_USER)
                .requestMatchers("/api/v1/auth/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/api-docs",
                        "/api-docs/**",
                        "/api-docs/swagger-config",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html").permitAll()
                .anyRequest().authenticated());

        // setting custom access denied handler for not authorized request
        http.exceptionHandling(x->x.accessDeniedHandler(new CustomAccessDeniedHandler()));

        // setting custom entry point for unauthenticated request
        http.exceptionHandling(x->x.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
