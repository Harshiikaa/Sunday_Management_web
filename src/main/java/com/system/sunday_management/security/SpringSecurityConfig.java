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

// these are the decoration for configuring and enabling the web security
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    //    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    private final CustomUserDetailService customUserDetailService;

    public SpringSecurityConfig(CustomUserDetailService customUserDetailService){
        this.customUserDetailService = customUserDetailService;
    }
    @Bean
    // yesley password j lekhyo teii form huncha  tara encoded form maa save huncha ani decode garna mildaina
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService((UserDetailsService) customUserDetailService );
        authenticationProvider.setPasswordEncoder(PasswordEncoderUtil.getInstance());
        return authenticationProvider;
    }
    // bean ley jailey yo file enable garcha
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
                // since localhost 8085 gardaa login khulena so tala ko code garney ani system ley banaako login page khulcha
                .and()
                .formLogin()
                // this will open haamiley banaako login page
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
