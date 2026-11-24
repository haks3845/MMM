package kh.rechargetech.mmm.member.service;

import kh.rechargetech.mmm.member.mapper.MemberMapper;
import kh.rechargetech.mmm.member.model.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public Member login(Member loginReq) {
        return memberMapper.loginMember(loginReq);
    }
}