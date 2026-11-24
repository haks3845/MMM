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
public class Board {
	private int boardNo;
	private int memberNo;
	private int boardType;
	private String boardTitle;
	private String boardCon;
	private Date boardModDate;
	private Date boardDate;
}
