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
    // 아이디 중복 확인
	public int checkId(String id) {
		return memberMapper.checkId(id);
	}
	// 닉네임 중복 확인
	public int checkNickName(String nickName) {
		return memberMapper.checkNickName(nickName);
	}
	// 회원가입
	public int enrollMember(Member member) {
		return memberMapper.enrollMember(member);
	}
}