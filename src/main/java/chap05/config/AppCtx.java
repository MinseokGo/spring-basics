package chap05.config;

import chap05.spring.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//스프링 설정 클래스
@Configuration
//spring 패키지에서 @Component 애노테이션을 붙인 클래스를
//스프링 컨테이너가 직접 검색해서 빈으로 등록해줌
//설정 코드가 줄어듦
//스캔 대상은 spring 패키지와 그 하위 패키지에 속한 클래스
//@Component 애노테이션이 붙은 클래스의 객체를 생성해서 빈으로 등록
@ComponentScan(basePackages = {"chap05.spring"})
public class AppCtx {
    //@Bean이 붙은 메서드에 대해 하나의 객체만을 생성하므로
    //아래의 코드에서 여러번 생성되는 것처럼 보이는 MemberDao 객체는 싱글톤 객체이다.
    //즉 memberDao()를 여러번 호출하여도 항상 같은 객체를 리턴한다.
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }


    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);

        return versionPrinter;
    }
}