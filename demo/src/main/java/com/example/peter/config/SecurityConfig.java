package com.example.peter.config;

import com.example.peter.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    private JwtAuthFilter authFilter;


//    @Bean
//    SecurityFilterChain getFilter(HttpSecurity http) throws Exception {
//
//        http.csrf(csrf -> csrf.ignoringRequestMatchers("users/createUser","users/authentication"));
//        http.authorizeHttpRequests(auth -> auth.requestMatchers("users/createUser","users/authentication")
//                        .permitAll().anyRequest().authenticated())
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                .httpBasic(basic->basic.init(http));
//        return http.build();
//
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/signUp/user","/login","/upload","/uploadEducation","/forgetPassword/{phoneNumber}","/resend/{phoneNumber}","/validate/{otp}","/workOrder").permitAll()
                        .requestMatchers(HttpMethod.GET, "/workOrder/{id}","/workOrder").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/deleteEducations","/deleteDocuments").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/workOrder/{id}").permitAll()
                        .anyRequest().authenticated())


//                .authorizeRequests(authorize -> authorize
//                        .antMatchers(HttpMethod.POST, "/users/createUser", "/users/authentication", "/upload", "/uploadEducation", "/send/{phoneNumber}").permitAll()
//                        .antMatchers(HttpMethod.PUT, "/users/updateUser/{id}").authenticated()
//                        .anyRequest().authenticated())



                .authenticationProvider(authenticationProvider()).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserInfoUserDetailsService();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
