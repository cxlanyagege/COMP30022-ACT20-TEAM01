/**
 * Class Name: MybatisPlusConfig
 * Description: Configuration for JPA ORM on MybatisPlus
 * 
 * Author: Dennis Wang & He Shen
 * Date: 2023/9/26
 */

package it.project.application.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration
@MapperScan("it.project.application.mapper*")
public class MybatisPlusConfig {

    // Mybatis Plus interceptor
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    // Custom Mybatis Plus config
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setMapUnderscoreToCamelCase(false);
    }
    
}
