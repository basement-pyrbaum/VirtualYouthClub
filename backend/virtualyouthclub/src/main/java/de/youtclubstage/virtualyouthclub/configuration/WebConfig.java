package de.youtclubstage.virtualyouthclub.configuration;

import de.youtclubstage.virtualyouthclub.security.SecurityInterceptor;
import de.youtclubstage.virtualyouthclub.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SecurityService securityService;

    @Autowired
    public WebConfig(@Lazy SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("Origin", "X-Requested-With", "Content-Type","authorization","X-XSRF-TOKEN")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor(securityService));
    }


}