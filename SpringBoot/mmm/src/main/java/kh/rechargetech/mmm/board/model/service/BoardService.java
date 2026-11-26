package kh.rechargetech.mmm.board.model.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import kh.rechargetech.mmm.board.model.mapper.BoardMapper;
import kh.rechargetech.mmm.board.model.vo.Board;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardMapper boardMapper;

	// 게시글 목록 조회
	public List<Board> selectBoardList() {
		 return boardMapper.selectBoardList();
    }

}
