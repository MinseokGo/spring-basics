package spring;

public class ChangePasswordService {
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
