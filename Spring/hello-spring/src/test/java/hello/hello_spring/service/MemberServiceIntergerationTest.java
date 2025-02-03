package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional  // Test가 끝나면 롤백을 해준다.
class MemberServiceIntergerationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memory;

    // 테스트는 한글로 작성 가능!
    @Test
    void 회원가입() {
        //given(데이터)
        Member member = new Member();
        member.setName("전우치");
        //when(검증)
        Long saveId = memberService.join(member);

        //then(결과)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외()
    {
        //given
        Member member1 = new Member();
        member1.setName("전우치");

        Member member2 = new Member();
        member2.setName("전우치");

        //when
        memberService.join(member1);
        Assertions.assertThatIllegalStateException().isThrownBy(() -> memberService.join(member2));
//        try {
//            memberService.join(member2);
//        }catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        //then
    }
}