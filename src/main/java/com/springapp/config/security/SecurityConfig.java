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
                .disable() // �������� ������ �� CSRF ����
                .authorizeRequests() // ��������� ������� �������� �� ������� ����� ����������� ������ � �������� � ��������� ������
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/json*").hasRole("ADMIN")
                .antMatchers("/pup*").hasRole("USER")
                //.antMatchers("/user**").hasRole("USER")
                .antMatchers("/user**").permitAll()
                .anyRequest().permitAll()
                .and();
        http.formLogin()
                .loginPage("/login")  // ��������� �������� � ������ ������
                .loginProcessingUrl("/j_spring_security_check") // ��������� action � ����� ������
                .failureUrl("/login?error") // ��������� URL ��� ��������� ������
                .usernameParameter("j_username") // ��������� ��������� ������ � ������ � ����� ������
                .passwordParameter("j_password")
                .permitAll(); // ���� ������ � ����� ������ ����
        http.logout()
                .permitAll() // ��������� ������ ������ ����
                .logoutUrl("/logout") // ��������� URL �������
                .logoutSuccessUrl("/login?logout") // ��������� URL ��� ������� �������
                .invalidateHttpSession(true); // ������ �� �������� ������� ������
    }

    @Bean
    public Md5PasswordEncoder getMD5PasswordEncoder(){return new Md5PasswordEncoder();}


    /*@Bean
    public SecurityFilterChain getSpringSecurityFilterChain(){

        return null;
    }*/
}

