package com.example.library_management.config;

import com.example.library_management.entity.User;
import com.example.library_management.enums.Department;
import com.example.library_management.enums.Gender;
import com.example.library_management.enums.Role;
import com.example.library_management.repository.UserRepo;
import com.example.library_management.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeHttpRequests().requestMatchers("/user/add").permitAll();
        http.authorizeHttpRequests().requestMatchers("/user/borrow/**","/user/return/**").hasAuthority("USER");
        http.authorizeHttpRequests().requestMatchers("/user/get","/book/add").hasAuthority("ADMIN").anyRequest().authenticated();
        http.httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("signup/hello");
    }



    @PostConstruct
    public void createAdmin(){
        if(userRepo.count()==0){
            System.out.println(userRepo.count()+".......");
            User admin=new User("admin","admin@gmail.com","admin","e19csr", Department.CSE,4,Role.ADMIN);
            userRepo.save(admin);
        }
    }

    @Bean
    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject
                (AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder());
        return authenticationManagerBuilder.build();
    }


}