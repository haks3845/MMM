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
	
	// 게시글 상세 조회
	public Board selectBoardDetail(int boardNo) {
		return boardMapper.selectBoardDetail(boardNo);
	}
	
	// 게시글 검색
	public List<Board> searchBoard(String searchType, String keyword) {
		return boardMapper.searchBoard(searchType, keyword);
	}
	
	// 게시글 페이지 이동
	public List<Board> selectBoardPage(int currentPage, int boardLimit) {
		int startRow = (currentPage - 1) * boardLimit + 1;
		int endRow = currentPage * boardLimit;
		return boardMapper.selectBoardPage(startRow, endRow);
	}
	
	// 게시글 개수 조회
	public int selectBoardCount() {
		return boardMapper.selectBoardCount();
	}

	

}
