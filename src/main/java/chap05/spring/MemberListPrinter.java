package chap05.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("listPrinter")
public class MemberListPrinter {
    private MemberDao memberDao;
    private MemberPrinter printer;

    public void printAll() {
        Collection<Member> members = memberDao.selectAll();
        members.forEach(m -> printer.print(m));
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    //1. @Qualifier를 통해 한정 값을 지정
    //2. setPrinter의 인자를 MemberSummaryPrinter 타입으로 바꾸어 빈 객체 주입
    //2번은 MemberSummaryPrinter 빈 객체가 1개일 때 가능
    @Qualifier("summaryPrinter")
    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }
}