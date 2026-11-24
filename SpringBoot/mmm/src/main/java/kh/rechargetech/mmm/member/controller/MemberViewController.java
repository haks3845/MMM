package kh.rechargetech.mmm.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.rechargetech.mmm.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mmm/member")
@RequiredArgsConstructor
public class MemberViewController {

    private final MemberService memberService;

    // 회원가입 화면
    @GetMapping("/signup")
    public String signupPage() {
        return "member/signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@RequestParam("memberId") String memberId,
                         @RequestParam("memberPw") String memberPw,
                         @RequestParam("memberPwCheck") String memberPwCheck,
                         @RequestParam("memberName") String memberName,
                         @RequestParam("nickname") String nickname,
                         Model model) {

        if (!memberPw.equals(memberPwCheck)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "member/signup";
        }

        // TODO: memberService에 회원가입 메서드 추가 후 연결
        // memberService.signup(memberId, memberPw, memberName, nickname);

        return "redirect:/";   // 가입 후 메인으로 이동 (경로는 환경에 맞게 조정)
    }
    
    // 로그인 화면
    @GetMapping("/login")
    public String loginPage() {
        return "member/login";   // templates/member/login.html
    }
}