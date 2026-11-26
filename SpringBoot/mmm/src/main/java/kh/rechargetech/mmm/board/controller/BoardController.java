package kh.rechargetech.mmm.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.rechargetech.mmm.board.model.service.BoardService;
import kh.rechargetech.mmm.board.model.vo.Board;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mmm/board")
public class BoardController {
	private final BoardService boardService;

		// 게시글
		// 게시글 목록 조회
		@GetMapping("/list")
	    public String selectBoardList(Model model) {
	        List<Board> boardList = boardService.selectBoardList();
	        model.addAttribute("boardList", boardList);
	        // 게시글 목록 조회 테스트
	        System.out.println(boardList);
	        model.addAttribute("boardList", boardList);
	        return "board/list";
		}
		// 게시글 상세 조회
		
		// 게시글 작성
		
		// 게시글 수정
		
		// 게시글 삭제
	
		// 게시글 페이지 이동
	
		// 게시글 검색
		
		
		// 댓글
		// 댓글 조회
		
		// 댓글 작성
		
		// 댓글 수정
		
		// 댓글 삭제
}