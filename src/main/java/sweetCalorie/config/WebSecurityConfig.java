package sweetCalorie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().disable()
                .csrf()
                .csrfTokenRepository(csrfTokenRepository())
                .and()
                .authorizeRequests()
                .antMatchers( "/css/**", "/images/**").permitAll()
                .antMatchers("/", "/users/register", "/users/login").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .key("remember Me Key")
                .rememberMeCookieName("rememberMeCookie")
                .tokenValiditySeconds(2678400)
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/users/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/");
    }

   private CsrfTokenRepository csrfTokenRepository(){
       HttpSessionCsrfTokenRepository repository =
               new HttpSessionCsrfTokenRepository();
       repository.setSessionAttributeName("_csrf");
       return repository;
   }
}