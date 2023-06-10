package chap07.main;

import chap07.Calculator;
import chap07.RecCalculator;
import chap07.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator cal = ctx.getBean("calculator", Calculator.class);
        //RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
        //실제 생성되는 빈 객체가 인터페이스를 상속 받았으면
        //해당 인터페이스를 통해 프록시를 생성함
        //결국 Calculator를 통해 프록시를 생성
        //위의 "calculator" 빈의 실제 타입은 Calculator를 상속한 프록시 타입.
        //RecCalculator로 타입 변환 불가(같은 자식)
        long fiveFact = cal.factorial(5);

        System.out.println("cal.factorial(5) = " + fiveFact);
        //스프링이 생성ㅎ란 프록시 타입의 객체
        System.out.println(cal.getClass().getName());

        ctx.close();
    }
}
