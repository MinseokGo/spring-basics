package chap07.config;

import chap07.Calculator;
import chap07.RecCalculator;
import chap07.aspect.ExeTimeAspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
//아래는 인터페이스가 아닌 자바 클래스를 상속받아 프록시를 생성
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {
    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }
}
