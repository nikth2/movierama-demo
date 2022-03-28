package assignment.movies.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    private static final String[] UNPROTECTED_URL_PATTERNS = {
        "/",
        "/error",
        "/static/**",
        "/app/**",
        "/movies/sort",
        "/movies/list",
        "/movies/rate",
        "/registration",
        "/actuator/**",
        "/health",
        "/_ah/start",
        "/h2-console/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(UNPROTECTED_URL_PATTERNS)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

        http.csrf()
                .ignoringAntMatchers(UNPROTECTED_URL_PATTERNS);
        http.headers()
                .frameOptions()
                .sameOrigin();
        http.logout().logoutSuccessUrl("/");
    }

    /**
     * Authentication manager bean.
     */
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
