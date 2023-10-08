package org.gfg.securitydemo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  MyUserService myUserService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().
                dataSource(dataSource).
                usersByUsernameQuery("select email, password, true from my_user where email = ?").
                authoritiesByUsernameQuery("select email, authorities from my_user where email = ?");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserService);
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // stored data by which i can login
//        auth.inMemoryAuthentication().
//                withUser("karan").
//                password("karan123").
//                authorities("dev").
//                and().
//                withUser("rashmi").
//                password("rashmi123").
//                authorities("qa");
//    }

    // authorities -> dev
    // roles  -> development

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().and().authorizeRequests().
                antMatchers("/developCode/**").hasAuthority("dev").
                antMatchers("/testCode/**").hasAuthority("qa").
                antMatchers("/deployCode/**").hasAnyAuthority("dev","qa").
                antMatchers("/home/**").permitAll().
                and().
                formLogin();
    }

    // encoder
    // spring boot security
    // u are using password which is encoded
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // first it is taking the pass from the browser on login page
    // it is trying to encode it
    // with the encoded pass, it is trying to match the stored data


}
//developCode, login , login, developCode