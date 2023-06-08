package chap06.main.config;

import chap06.spring.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링 설정 클래스
@Configuration
public class AppCtx {
    @Bean
    public Client client() {
        System.out.println("Bean create! 1");
        Client client = new Client();
        client.setHost("go");

        return client;
    }

    @Bean(initMethod = "connect", destroyMethod = "close")
    public Client2 client2() {
        System.out.println("Bean create! 2");
        Client2 client = new Client2();
        client.setHost("go");

        return client;
    }
}