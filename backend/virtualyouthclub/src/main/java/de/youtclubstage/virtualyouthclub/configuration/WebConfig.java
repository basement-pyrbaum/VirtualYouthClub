package de.youtclubstage.virtualyouthclub.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("Origin", "X-Requested-With", "Content-Type","authorization","X-XSRF-TOKEN")
                .allowedMethods("*")
                .allowedOrigins("*");
    }


}