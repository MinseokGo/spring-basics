package chap04.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MemberPrinter {
    private DateTimeFormatter dateTimeFormatter;

    public MemberPrinter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    public void print(Member member) {
        if(dateTimeFormatter == null) {
            System.out.printf(
                    "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                    member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime()
            );
        } else {
            System.out.printf(
                    "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                    member.getId(), member.getEmail(), member.getName(), dateTimeFormatter.format(member.getRegisterDateTime())
            );
        }
    }

    //NPE 방지
    //1. 빈 객체 의존 주입이 필수가 아닐 때
    //대상 빈이 존재하지 않으면 메서드 호출 안함
    /*@Autowired(required = false)
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }*/

    //2. null이 가능할 때
    //@Autowired(required = false)와 다른 점은
    //빈이 존재하지 않아도 해당 메서드가 호출된다는 점
    @Autowired
    public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    //3. Optional을 통해 NPE 방지.
    //일치하는 빈이 존재하지 않으면 값이 없는 Optional을 인자로 전달.
    /*@Autowired
    public void setDateTimeFormatter(Optional<DateTimeFormatter> formatterOpt) {
        if(formatterOpt.isPresent()) {
            this.dateTimeFormatter = formatterOpt.get();
        } else {
            this.dateTimeFormatter = null;
        }
    }*/
}