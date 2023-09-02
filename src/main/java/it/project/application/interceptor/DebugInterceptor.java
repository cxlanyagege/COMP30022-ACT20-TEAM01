package it.project.application.interceptor;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class DebugInterceptor implements HandlerInterceptor {

    //private static final Logger logger = LoggerFactory.getLogger(DebugInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            //logger.debug("Authentication present: {}", authentication.getName());
        } else {
            //logger.debug("No authentication present");
        }
        return true;
    }
}
