package sweetCalorie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sweetCalorie.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/", "/users/login", "/users/register").anonymous()
                .and()
                .exceptionHandling().accessDeniedPage("/")
                .and().formLogin()
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .key("remember Me Key")
                .rememberMeCookieName("rememberMeCookie")
                .tokenValiditySeconds(2678400)
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/");

    }
}