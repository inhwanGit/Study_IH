package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // controller 와 service를 연결
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
