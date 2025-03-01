//package com.example.yy_demo.logging;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.logging.Logger;
//
//@Component
//public class LoggingInterceptor implements HandlerInterceptor {
//    private static final Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        logger.info("REQUEST: " + request.getMethod() + " " + request.getRequestURI());
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        logger.info("RESPONSE: " + response.getStatus());
//    }
//}