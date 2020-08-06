package com.example.project.api.accessToken;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
@Component
public class AccessTokenListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("服务启动");
        new AccessTokenThread().start();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("服务关闭");
    }
}
