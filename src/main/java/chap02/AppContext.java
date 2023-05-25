package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //해당 클래스를 스프링 설정 클래스로 지정
public class AppContext {
    //스프링 객체 생성 및 초기화 메서드
    //@Bean 애노테이션은 해당 메서드가 생성한 객체를 스프링이 관리하는 빈 객체로 등록하게 함
    @Bean
    //chap02.Greeter라는 빈(Bean) 객체와 greeter라는 빈 객체 이름
    //빈 객체 이름은 실제 빈 객체를 가리성
    public Greeter greeter() {
        Greeter g = new Greeter();
        g.setFormat("%s, 안녕하세요!");

        return g;
    }
    //위의 greeter 빈 객체와 다른 빈 객체를 생성함
    @Bean
    public Greeter greeter1() {
        Greeter g = new Greeter();

        return g;
    }
}
