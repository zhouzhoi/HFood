package com.zhou.common;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//处理跨域资源共享（CORS）问题
//CORS 是一种浏览器的安全机制，用于控制不同源的客户端 Web 应用能否访问服务器上的资源。
public class CorsConfig implements WebMvcConfigurer {
 @Override
 public void addCorsMappings(CorsRegistry registry) {
  //允许所有的 URL 路径进行跨域访问。
 registry.addMapping("/**")
 //是否发送Cookie
         //允许发送 Cookie
 .allowCredentials(true)
 //放⾏哪些原始域
         //允许任意来源的域名进行跨域访问
 .allowedOriginPatterns("*")
         //允许哪些 HTTP 请求方法进行跨域访问。
 .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
         //允许任意请求头
 .allowedHeaders("*")
         //允许任意响应头
 .exposedHeaders("*");
 }
}