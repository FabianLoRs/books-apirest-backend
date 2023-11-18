package com.company.books.backend.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
/*import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;*/
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.company.books.backend.filter.JwtReqFilter;

@Configuration
public class ConfigSecurity {

    @Autowired
    @Lazy
    private JwtReqFilter jwtReqFilter;

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configure -> {
            configure
                    .requestMatchers(HttpMethod.GET, "/v1/books").hasRole("Employee")
                    .requestMatchers(HttpMethod.GET, "/v1/books/**").hasRole("Employee")
                    .requestMatchers(HttpMethod.POST, "/v1/books").hasRole("Manager")
                    .requestMatchers(HttpMethod.PUT, "/v1/books/**").hasRole("Manager")
                    .requestMatchers(HttpMethod.DELETE, "/v1/books/**").hasRole("Manager")
                    //.requestMatchers(HttpMethod.GET, "/v1/categories").hasRole("Employee")
                    .requestMatchers(HttpMethod.GET, "/v1/categories/**").hasRole("Employee")
                    .requestMatchers(HttpMethod.POST, "/v1/categories").hasRole("Manager")
                    .requestMatchers(HttpMethod.PUT, "/v1/categories/**").hasRole("Manager")
                    .requestMatchers(HttpMethod.DELETE, "/v1/categories/**").hasRole("Manager")
                    .requestMatchers("/v1/categories", "/v1/authenticate", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                    .permitAll();
        })
                .addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
     * @Bean
     * public InMemoryUserDetailsManager userDetailsManager() {
     * 
     * UserDetails firstUser = User.builder()
     * .username("firstUser")
     * .password("{noop}hola.123")
     * .roles("Employee")
     * .build();
     * 
     * UserDetails secondUser = User.builder()
     * .username("secondUser")
     * .password("{noop}hola.123")
     * .roles("Employee", "Senior")
     * .build();
     * 
     * UserDetails thirdUser = User.builder()
     * .username("thirdUser")
     * .password("{noop}hola.123")
     * .roles("Employee","Manager")
     * .build();
     * return new InMemoryUserDetailsManager(firstUser, secondUser, thirdUser);
     * 
     * }
     */

}
