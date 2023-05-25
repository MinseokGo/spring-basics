package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {
    //@Autowired를 통해 해당 타입을 가진 빈 객체 주입
    //Setter가 필요 없음
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberPrinter printer;

    public void printMemberInfo(String email) {
        Member member = memberDao.selectByEmail(email);

        if(member == null) {
            System.out.println("데이터 없음\n");
            return ;
        }
        printer.print(member);
        System.out.println();
    }

    /*public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }*/
}