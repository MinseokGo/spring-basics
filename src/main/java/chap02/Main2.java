package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
        //별도의 설정을 하지 않을 경우 스프링은 한 개의 빈 객체만을 생성함
        //ex) 스프링은 기본적으로 한개의 @Bean 애노테이션에 대해 한개의 빈 객체를 생성
        //이때 빈 객체(greeter라는 이름을 가진 빈 객체)는 싱글톤(단일 객체) 범위를 가짐
        Greeter g1 = ctx.getBean("greeter", Greeter.class);
        Greeter g2 = ctx.getBean("greeter", Greeter.class);
        //g1과 g2는 같은 빈 객체를 가리킴. 즉 같은 객체
        System.out.println(g1 == g2);
    }
}
