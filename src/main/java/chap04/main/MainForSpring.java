package chap04.main;

import chap04.config.AppCtx;
import chap04.spring.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForSpring {
    private static ApplicationContext ctx = null;

    private static void processNewCommand(String[] arg) {
        if(arg.length != 5) {
            printHelp();
            return ;
        }

        MemberRegisterService regSvc = ctx.getBean("memberRegisterService", MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if(!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인 암호가 일치하지 않습니다.\n");
            return ;
        }
        try {
            regSvc.regist(req);
            System.out.println("등록했습니다.\n");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일입니다.\n");
        }
    }
    private static void processChangeCommand(String[] arg) {
        if(arg.length != 4) {
            printHelp();
            return ;
        }
        ChangePasswordService changePwdSvc = ctx.getBean("changePasswordService", ChangePasswordService.class);

        try {
            changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호를 변경했습니다.\n");
        } catch(MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다.\n");
        } catch(WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다.\n");
        }
    }
    static void printHelp() {
        System.out.println();
        System.out.println("잘못된 멍령입니다. 아래 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법 :");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println("info 이메일");
        System.out.println();
    }

    private static void processListCommand() {
        MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
        listPrinter.printAll();
    }

    private static void processInfoCommand(String[] arg) {
        if(arg.length != 2) {
            printHelp();
            return ;
        }

        MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
        infoPrinter.printMemberInfo(arg[1]);
    }

    private static void processVersionCommand() {
        //VersionPrinter versionPrinter = ctx.getBean(VersionPrinter.class);
        //위 코드는 스프링 설정 파일에서 getBean()의 파라미터인 해당 타입의 빈을 주입 시킨다.
        //하지만 AppCtx에 해당 타입의 빈이 두개(현재 버전과, Old 버전이 존재)이므로 예외가 발생한다.
        //즉, 해당 스프링 빈 객체 타입이 하나만 존재해야 위 코드 처럼 사용할 수 있다.
        VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }

    public static void main(String[] args) throws IOException {
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("명령어를 입력하세요 : ");
            String command = reader.readLine();
            if(command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            }
            if(command.startsWith("new ")) {
                processNewCommand(command.split("\\s"));
                continue;
            } else if(command.startsWith("change ")) {
                processChangeCommand(command.split("\\s"));
                continue;
            } else if(command.equals("list")) {
                processListCommand();
                continue;
            } else if(command.startsWith("info ")) {
                processInfoCommand(command.split(" "));
                continue;
            } else if(command.equals("version")) {
                processVersionCommand();
                continue;
            }
            printHelp();
        }
    }
}