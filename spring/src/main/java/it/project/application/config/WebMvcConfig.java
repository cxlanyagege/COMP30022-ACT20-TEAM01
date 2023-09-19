/**
 * Class Name: WebMvcConfig
 * Description: Configuration for web interceptor/debugger
 * 
 * Author: He Shen
 * Date: 2023/8/16
 * 
 * Note: Not required in production
 */

package it.project.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.project.application.interceptor.DebugInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private DebugInterceptor debugInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(debugInterceptor);
    }
}
