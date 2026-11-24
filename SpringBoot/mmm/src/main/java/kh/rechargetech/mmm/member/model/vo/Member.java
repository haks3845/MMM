package kh.rechargetech.mmm.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class Member {
	private int memberNo;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String nickname;
    private String status;
    private String isAdmin;
    private Date enrollDate;
    private Date updateDate;
}
