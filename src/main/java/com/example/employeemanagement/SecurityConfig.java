package com.example.employeemanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    //creating users(admin and user)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        //admin
        UserDetails admin= User.withUsername("admin").password(encoder.encode("admin123"))
                .roles("ADMIN").build();

        //user
        UserDetails user=User.withUsername("user").password(encoder.encode("user123"))
                .roles("USER").build();

        //storing users in memory not in database
        return new InMemoryUserDetailsManager(admin,user);
    }


    // Configuring who can access which endpoint
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/v3/api-docs/**","/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/employees/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/employees").hasRole("ADMIN")
                        .requestMatchers("/employees/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());

        return http.build();
    }


    //password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
