package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    // 프로그램 실행전 무조건 실행
    @BeforeEach
    public void setUp() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    //MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){

        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        memberService.findMember(1L);

        //then
        Assertions.assertThat(memberService.findMember(1L)).isEqualTo(member);
    }
}
