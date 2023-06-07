package chap03.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap03.spring.*;

@Configuration
public class AppCtx2 {
    //@Autowired 애노테이션을 붙이면 해당 타입의 빈을 찾아서 필드에 할당
    //AppCtx1 클래스에 MemberDao 타입의 빈을 설정했으므로
    //AppCtx2 클래스의 memberDao 필드에는 AppCtx1 클래스에서 설정한 빈이 할당됨
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberPrinter memberPrinter;

    @Bean
    public MemberRegisterService memberRegisterService() {
        return new MemberRegisterService(memberDao);
    }

    @Bean
    public ChangePasswordService changePasswordService() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);

        return pwdSvc;
    }

    @Bean
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter(memberDao, memberPrinter);
    }

    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();

        /*infoPrinter.setMemberDao(memberDao);
        infoPrinter.setPrinter(memberPrinter);*/

        return infoPrinter;
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);

        return versionPrinter;
    }
}