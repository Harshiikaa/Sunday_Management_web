package com.system.sunday_management.security;

import com.system.sunday_management.config.PasswordEncoderUtil;
//import com.system.sunday_management.service.impl.CustomerUserDetailService;
import com.system.sunday_management.service.impl.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private final CustomUserDetailService customUserDetailService;
    public SpringSecurityConfig(CustomUserDetailService customUserDetailService){
        this.customUserDetailService = customUserDetailService;
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService((UserDetailsService) customUserDetailService );
        authenticationProvider.setPasswordEncoder(PasswordEncoderUtil.getInstance());
        return authenticationProvider;
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        // csrf = cross site request forgery
        httpSecurity.csrf().disable()
                // jun jun page render garney teslaii permit diney
                .authorizeHttpRequests()
                .requestMatchers("/login","/user/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user/dashboard",true)
                .usernameParameter("email")
                .permitAll()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/css/**", "/images/**", "/js/**");
    }
}
