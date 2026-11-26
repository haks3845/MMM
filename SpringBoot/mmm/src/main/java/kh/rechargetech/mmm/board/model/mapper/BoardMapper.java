package kh.rechargetech.mmm.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.rechargetech.mmm.board.model.vo.Board;

@Mapper
public interface BoardMapper {

	// 게시글 목록 조회
	List<Board> selectBoardList();
	
    // 게시글 상세 조회

    // 게시글 작성

    // 게시글 수정

    // 게시글 삭제
    
    // 게시글 페이지 이동

    // 게시글 검색
}
