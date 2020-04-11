package de.youtclubstage.virtualyouthclub.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {// @formatter:off
        http.cors()
                .and()
                .authorizeRequests(a -> a
                        .antMatchers("/api/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling()
                .and()
                .oauth2ResourceServer().jwt();
    }//@formatter:on

}