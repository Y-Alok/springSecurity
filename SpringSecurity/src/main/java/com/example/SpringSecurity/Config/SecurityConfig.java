package com.example.SpringSecurity.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    for inmemory
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails student = User.builder().username("alok")
//                .password(getPasswordEncoder().encode("alok123"))
//                .roles("STUDENT")
//                .build();
//
//        UserDetails teacher = User.builder().username("pritesh")
//                .password(getPasswordEncoder().encode("pritesh123"))
//                .roles("TEACHER")
//                .build();
//        return new InMemoryUserDetailsManager(student,teacher);
//    }

    //Having database now
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    public static final String[] PUBLIC_URLS = {"/v3/api-docs",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/swagger-ui/**",
            "/person/**"};
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/person/**").permitAll()
//                        .requestMatchers("/swagger-ui/index.html#/**").permitAll()
//                        .requestMatchers("/v3/api-docs").permitAll()
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .requestMatchers("/student/**").hasAnyRole("STUDENT","ADMIN")
                        .requestMatchers("/teacher/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }

}
