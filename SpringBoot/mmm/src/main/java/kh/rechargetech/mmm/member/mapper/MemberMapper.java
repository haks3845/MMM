package kh.rechargetech.mmm.member.mapper;

import kh.rechargetech.mmm.member.model.vo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member loginMember(Member loginReq);
}