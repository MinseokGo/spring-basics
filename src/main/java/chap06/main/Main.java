package chap06.main;

//자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리
import chap06.spring.Client;
import chap06.main.config.AppCtx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Client client = ctx.getBean(Client.class);
        client.send();

        ctx.close();
    }
}