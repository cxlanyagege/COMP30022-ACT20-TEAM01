/**
 * Class Name: WebConfig
 * 
 * Description: Website configuration
 * 
 * Author: Dennis Wang & He Shen
 * 
 * Date: 2023/10/20
 */

package it.project.application.config;

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

    // allow website to have access to the file preview
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(accessPath + "**").addResourceLocations("file:" + localPath);
    }

    // allow access from every port
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}