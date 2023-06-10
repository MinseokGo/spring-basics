package chap07.main;

import chap07.Calculator;
import chap07.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator cal = ctx.getBean("calculator", Calculator.class);
        long fiveFact = cal.factorial(5);

        System.out.println("cal.factorial(5) = " + fiveFact);
        //스프링이 생성ㅎ란 프록시 타입의 객체
        System.out.println(cal.getClass().getName());

        ctx.close();
    }
}
