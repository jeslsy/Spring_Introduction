package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    // Autowired = spring이 컨테이너에 있는 memberService를 가져와줌
    // 이게바로 의존 관계 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }


    @PostMapping("/members/new")
    public String create(MemberForm form){                
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        // member를 다 꺼내옴
        List<Member> members = memberService.findMembers();
        // 그걸 model에 다 넣음
        model.addAttribute("members", members);
        //model과 함께 return으로 전달
        return "members/memberList";
    }
}
