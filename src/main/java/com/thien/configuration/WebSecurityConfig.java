package com.thien.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(value="org.webapp")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource restDataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/login/**", "/css/**", "/js/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login").usernameParameter("username").passwordParameter("password")
//                    .permitAll()
//                    .failureForwardUrl("/")
                    .and()
                .logout()
                    .permitAll()
                .and().csrf().disable();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("password").roles("USER", "ADMIN");
//    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(restDataSource)
                .usersByUsernameQuery("select pwd from users where username=?")
                /*.authoritiesByUsernameQuery("select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?").passwordEncoder(passwordencoder())*/;
    }

    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }
}