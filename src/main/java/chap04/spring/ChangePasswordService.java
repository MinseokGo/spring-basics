package chap04.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ChangePasswordService {
    //@Autowired를 사용하면 스프링은 타입이 일치하는 빈 객체를 찾아서 주입해줌
    @Autowired
    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void changePassword(String email, String oldPassword, String newPassword) throws MemberNotFoundException, WrongIdPasswordException {
        Member member = memberDao.selectByEmail(email);
        if(member == null) {
            throw new MemberNotFoundException();
        }
        //이전 비밀번호와 변경하려는 비밀번호 불일치시 Wro
        member.changePassword(oldPassword, newPassword);

        memberDao.update(member);
    }
}
