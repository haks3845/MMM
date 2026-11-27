package kh.rechargetech.mmm.member.mapper;

import kh.rechargetech.mmm.member.model.vo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member loginMember(Member loginReq);
    // 아이디 중복 확인
	int checkId(String id);
	// 닉네임 중복 확인
	int checkNickName(String nickName);
	// 회원가입
	int enrollMember(Member member);
}