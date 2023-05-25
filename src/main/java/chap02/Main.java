package chap02;
//자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        //빈 객체 생성
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
        //getBean() 메서드의 파라미터는
        //@Bean 애노테이션의 메서드 이름인 빈 객체의 이름과 검색할 빈 객체의 타입
        //빈 객체 제공
        Greeter g = ctx.getBean("greeter", Greeter.class);
        String msg = g.greet("스프링");    //String.format("%s, 안녕하세요!", "스프링")
        System.out.println(msg);
        ctx.close();
    }
}