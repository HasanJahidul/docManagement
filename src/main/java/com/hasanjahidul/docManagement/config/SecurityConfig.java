package com.hasanjahidul.docManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private PermissionFilter permissionFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    @Bean
    protected SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults()) // Enable CORS configuration
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection as you're relying on JWT
                .authorizeRequests()
                .requestMatchers(req -> true)
                .permitAll()
                .and()
                .addFilterBefore(new PermissionFilter(), UsernamePasswordAuthenticationFilter.class); // Add the permission filter and skip the UsernamePasswordAuthenticationFilter

        return http.build();
    }


    /**
     * Cors configuration source cors configuration source.
     *
     * @return the cors configuration source
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:3000/");
//        config.addAllowedOrigin("https://demo.merlinapp.co.uk/");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}