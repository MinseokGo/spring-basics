package main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.*;
import spring.MemberInfoPrinter;
import spring.VersionPrinter;

//스프링 설정 클래스
@Configuration
public class AppCtx {
    //@Bean이 붙은 메서드에 대해 하나의 객체만을 생성하므로
    //아래의 코드에서 여러번 생성되는 것처럼 보이는 MemberDao 객체는 싱글톤 객체이다.
    //즉 memberDao()를 여러번 호출하여도 항상 같은 객체를 리턴한다.
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegisterService() {
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePasswordService() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());

        return pwdSvc;
    }
    //만약 주입할 객체가 빈이 아니라 일반 객체를 생성해서 주입한다면?
    //private MemberPrinter printer new MemberPrinter(); Not Bean
    //이때의 차이점은 스프링 빈 객체로 등록할 때와 아닐 때, 스프링 컨테이너가 객체를 관리하는지의 여부
    //위의 코드와 같이 설정하면 MemberPrinter를 빈으로 등록하지 않으므로 스프링 컨테이너에서 MemberPrinter를 구할 수 없음
    //ex) MemberPrinter printer = ctx.getBean(MemberPrinter.class); 는 오류
    //빈 객체가 아니기 때문!!
    //하지만 객체를 생성(일반 객체)하였으므로 listPrinter, infoPrinter의 이름을 가진 빈을 생성했고, 이렇게 해도
    //MemberListPrinter 객체와 MemberInfoPrinter 객체는 정상적으로 동작한다.
    //-> 스프링 컨테이너는 자동 주입, 라이프사이클 관리 등 단순 객체 생성 외에 객체 관리를 위한 다양한 기능을 제공하는데
    //빈으로 등록한 객체에만 기능을 적용한다.
    //스프링 컨테이너가 제공하는 관리 기능이 필요 없고, getBean() 메서드로 구할 필요가 없다면 꼭 빈 객체로 등록해야하는 것은 아님
    //의존 주입 대상은 스프링 빈으로 등록하는 것이 보통이다.
    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

    @Bean
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter(memberDao(), memberPrinter());
    }

    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();

        /*infoPrinter.setMemberDao(memberDao());
        infoPrinter.setPrinter(memberPrinter());*/

        return infoPrinter;
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);

        return versionPrinter;
    }

    @Bean
    public VersionPrinter oldVersionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(4);
        versionPrinter.setMinorVersion(3);

        return versionPrinter;
    }
}