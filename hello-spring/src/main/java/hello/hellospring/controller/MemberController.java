package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller //스프링 컨테이너가 생성될 때 Controller 애노테이션이 있으면 컨테이너넣고 관리한다.
public class MemberController {

    //private final MemberService memberService = new MemberService(); //이렇게 new로 생성해서 쓸 수 있지만 스프링이 관리하게 되면 스프링 컨테이너로 부터 받아서 쓰도록 바꿔야 한다.
    private final MemberService memberService;

    @Autowired //스프링 컨테이너에서 MemberService를 가져온다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        //System.out.println(form.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {

        List<Member> members = memberService.findMembers();

        model.addAttribute("members", members);

        return "members/memberList";
    }

    @RequestMapping(value="/test", method= RequestMethod.POST)
    @ResponseBody
    public String stringify(@RequestBody Map<String, Object> param) {

        String a = (String) param.get("id");
        System.out.println(a);

        return "성공인가?";
    }
}
