
package lk.dialog.BillVerification.security;

import lk.dialog.BillVerification.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;
    private final TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    public WebSecurityConfig(AuthenticationProvider authenticationProvider, TokenAuthenticationService tokenAuthenticationService) {
        this.authenticationProvider = authenticationProvider;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable caching
        http.headers().cacheControl();
        http.csrf().disable() // disable csrf for our requests.
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/token/**").permitAll()
                .antMatchers("/rest/**").authenticated()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .and()
                //todo Cross Origin filter should be a after one
                .addFilterBefore(new CrossOriginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager(), tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new AuthenticationFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}