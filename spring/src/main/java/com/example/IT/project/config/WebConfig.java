package com.example.IT.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.accessPath}")
    private String accessPath;

    @Value("${upload.localPath}")
    private String localPath;

    // 帮助浏览器访问图片
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(accessPath + "**").addResourceLocations("file:" + localPath);
    }

    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}