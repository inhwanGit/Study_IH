package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // DI에는 필드주입, settet 주입, 생성자 주입 3가지가 방법이 존재. 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장.

    // @Autowired // controller 와 service를 연결

    private  MemberService memberService;

    // 필드 주입 // 단점 : 필드 주입은 중간에 바꿀 수 있는 방법이 아예 없다(생성자와 Setter에 비해)
//    @Autowired private MemberService memberService;

    // Setter 주입 // 단점 : 누군가가 멤버서비스를 호출했을때 public으로 열려 있어야 된다.
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // 생성자 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String creatForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String creat(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
