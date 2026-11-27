package kh.rechargetech.mmm.member.controller;

import jakarta.servlet.http.HttpSession;
import kh.rechargetech.mmm.member.model.vo.Member;
import kh.rechargetech.mmm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mmm/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member loginReq, HttpSession session) {
        Member loginUser = memberService.login(loginReq);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(loginUser);
        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
            return ResponseEntity.ok(loginUser); // 로그인 성공 시 회원 정보 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    // 로그아웃 API
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(loginUser);
    }
    
    
    // 아이디 중복 확인 API
    @GetMapping("/checkId")
    public String checkId(@RequestParam("id") String id) {
        int count = memberService.checkId(id);
        return String.valueOf(count);
    }

    // 닉네임 중복 확인 API
    @GetMapping("/checkNickName")
    public String checkNickName(@RequestParam("nickName") String nickName) {
        int count = memberService.checkNickName(nickName);
        return String.valueOf(count);
    }
    
    // 회원가입 API (HTML <form action="/mmm/api/member/enroll" method="POST"> 처리)
    @PostMapping("/enroll")
    public ResponseEntity<String> enrollMember(Member member) {
        int result = memberService.enrollMember(member);
        
        if (result > 0) {
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("회원가입에 실패했습니다.");
        }
    }
    
}