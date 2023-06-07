package chap04.spring;

public class Main {
    public static void main(String[] args) throws WrongIdPasswordException, MemberNotFoundException {
        Assembler assembler = new Assembler();

        ChangePasswordService changePwdSvc = assembler.getPwdSvc();
        changePwdSvc.changePassword("rhalstjr1999@naver.com", "1234", "newpassword");
    }
}
