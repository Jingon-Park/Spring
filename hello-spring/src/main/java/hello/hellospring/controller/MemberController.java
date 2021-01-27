package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //스프링 컨테이너가 생성될 때 Controller 애노테이션이 있으면 컨테이너넣고 관리한다.
public class MemberController {

    //private final MemberService memberService = new MemberService(); //이렇게 new로 생성해서 쓸 수 있지만 스프링이 관리하게 되면 스프링 컨테이너로 부터 받아서 쓰도록 바꿔야 한다.
    private final MemberService memberService;

    @Autowired //스프링 컨테이너에서 MemberService를 가져온다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

    }

}
