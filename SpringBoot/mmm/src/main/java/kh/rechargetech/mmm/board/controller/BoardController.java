package kh.rechargetech.mmm.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.rechargetech.mmm.board.model.service.BoardService;
import kh.rechargetech.mmm.board.model.vo.Board;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mmm/board")
public class BoardController {
	private final BoardService boardService;

		// 게시글
		// 게시글 목록 조회 / REQ-BRD-001
		@GetMapping("/list")
	    public String selectBoardList(Model model) {
	        List<Board> boardList = boardService.selectBoardList();
	        // View에 데이터 전달
	        model.addAttribute("boardList", boardList);
	        // 게시글 목록 조회 테스트
	        //System.out.println(boardList);
	        return "board/list";
		}
		// 게시글 상세 조회 / REQ-BRD-002
		@GetMapping("/detail")
		public String selectBoardDetail(@RequestParam("boardNo") int boardNo, Model model) {
			Board board = boardService.selectBoardDetail(boardNo);
			model.addAttribute("board", board);
			// 게시글 상세 조회 테스트
		    //System.out.println(board);
			return "board/detail";
		}
		
		// 게시글 검색 / REQ-BRD-003
		@GetMapping("/search")
		public String searchBoard(@RequestParam("searchType") String searchType, @RequestParam("keyword") String keyword, Model model) {
			keyword = keyword.trim();
		    // 검색어가 비어 있으면 게시판으로 이동
		    if (keyword.isEmpty()) {
		        return "redirect:/mmm/board/page";
		    }
		    
		    List<Board> boardList = boardService.searchBoard(searchType, keyword);
		    model.addAttribute("boardList", boardList);
		    model.addAttribute("searchType", searchType);
		    model.addAttribute("keyword", keyword);
		    // 게시글 검색 테스트
		    //System.out.println("검색 조건 : " + searchType);
		    //System.out.println("검색어 : " + keyword);
		    //System.out.println("검색 결과 수 : " + boardList.size());
		    //System.out.println(boardList);
		    return "board/list";
		}

		// 게시글 페이지 이동 / REQ-BRD-004
		@GetMapping("/page")
		public String selectBoardPage(@RequestParam(value = "page", defaultValue = "1") int currentPage, Model model) {
			
			// 한 페이지에 보여줄 게시글 개수
			int boardLimit = 10;
			
			// 게시글 개수 조회
			int listCount = boardService.selectBoardCount();
			
			// 페이지 개수
			int maxPage = Math.max(1, (int) Math.ceil((double) listCount / boardLimit));
			
			// 현재 페이지 범위 제한
			currentPage = Math.max(1, Math.min(currentPage, maxPage));
			
			// 현재 페이지 게시글 조회
			List<Board> boardList = boardService.selectBoardPage(currentPage, boardLimit);
			
			model.addAttribute("boardList", boardList);
			model.addAttribute("listCount", listCount);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("maxPage", maxPage);
			
			// 페이지 이동 테스트
			//System.out.println("전체 게시글 수 : " + listCount);
			//System.out.println("현재 페이지 : " + currentPage);
			//System.out.println("전체 페이지 : " + maxPage);
			//System.out.println(boardList);
			
			return "board/list";
		}
		

		// 게시글 작성
		
		// 게시글 수정
		
		// 게시글 삭제
	
		
		
		// 댓글
		// 댓글 조회
		
		// 댓글 작성
		
		// 댓글 수정
		
		// 댓글 삭제
}