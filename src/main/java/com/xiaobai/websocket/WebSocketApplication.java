package com.xiaobai.websocket;

import com.xiaobai.websocket.server.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebSocketApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebSocketApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        //解决WebSocket不能注入的问题
        WebSocketServer.setApplicationContext(configurableApplicationContext);
    }

}
