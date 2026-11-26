package kh.rechargetech.mmm.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {
	private int comNo;
	private int boaorNo;
	private int memberNo;
	private String comCon;
	private Date comDate;
	private Date comModDate;
}
