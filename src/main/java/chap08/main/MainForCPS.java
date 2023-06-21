package chap08.main;

import chap08.config.AppCtx;
import chap08.spring.ChangePasswordService;
import chap08.spring.MemberNotFoundException;
import chap08.spring.WrongIdPasswordException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForCPS {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        ChangePasswordService cps = ctx.getBean("changePasswordService", ChangePasswordService.class);

        try {
            cps.changePassword("rhalstjr1999@naver.com", "1234", "1111");
            System.out.println("change password");
        } catch (MemberNotFoundException e) {
            System.out.println("Not exist Member info!!");
        } catch (WrongIdPasswordException e) {
            System.out.println("Not correct password!!");
        }

        ctx.close();
    }
}