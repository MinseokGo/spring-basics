package chap07.config;

import chap07.Calculator;
import chap07.RecCalculator;
import chap07.aspect.CacheAspect;
import chap07.aspect.ExeTimeAspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppCtxWithCache {
    //cache보다 exeTime을 먼저 선언하면 실행시간이 전부 뜸..
    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect();
    }

    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }
}