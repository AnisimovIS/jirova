package com.springapp.config.security;

import com.springapp.services.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    Logger log;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        try {
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(getMD5PasswordEncoder());
        }catch (Exception e){
            log.warning(e.getMessage());
        }

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable() // включаем защиту от CSRF атак
                .authorizeRequests() // указываем правила запросов по которым будет определятся доступ к ресурсам и остальным данным
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/json*").hasRole("ADMIN")
                .antMatchers("/pup*").hasRole("USER")
                //.antMatchers("/user**").hasRole("USER")
                .antMatchers("/user**").permitAll()
                .anyRequest().permitAll()
                .and();
        http.formLogin()
                .loginPage("/login")  // указываем страницу с формой логина
                .loginProcessingUrl("/j_spring_security_check") // указываем action с формы логина
                .failureUrl("/login?error") // указываем URL при неудачном логине
                .usernameParameter("j_username") // Указываем параметры логина и пароля с формы логина
                .passwordParameter("j_password")
                .permitAll(); // даем доступ к форме логина всем
        http.logout()
                .permitAll() // разрешаем делать логаут всем
                .logoutUrl("/logout") // указываем URL логаута
                .logoutSuccessUrl("/login?logout") // указываем URL при удачном логауте
                .invalidateHttpSession(true); // делаем не валидной текущую сессию
    }

    @Bean
    public Md5PasswordEncoder getMD5PasswordEncoder(){return new Md5PasswordEncoder();}


    /*@Bean
    public SecurityFilterChain getSpringSecurityFilterChain(){

        return null;
    }*/
}

