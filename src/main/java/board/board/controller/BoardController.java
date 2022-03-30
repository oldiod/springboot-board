package board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.board.dto.BoardDto;
import board.board.service.BoardService;

@Controller//스프링 mvc의 컨트롤러를 의미함 @Controller 어노테이션을 붙여줌으로써 해당클래스를 컨트롤러로 동작하게 합니다.
public class BoardController {
	
	/*비즈니스 로직을 처리하는 서비스 빈을 연결합니다.*/
	@Autowired
	private BoardService boardService;
	
	/*RequestMapping 어노테이션의 값으로 주소를 지정합니다.
	 * 그 값으로 /board/openBoardList.do가 지정되어 있는데 웹브라우저에서
	 * /board/openBoardList.do라는 주소를 호출하면 스프링 디스패처는 호출된 주소와 @RequestMapping 어노테이션의 값이 동일한 메서드를
	 *찾아서 실행합니다. 즉 클라이언트에서 호출한 주소와 그것을 수행할 메서드를 연결합니다.*/
	//게시글 조회
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception{
		
//		호출에 요청된 결과를 보여줄 뷰를 지정합니다. 여기서는 /board/boardlist로 지정했는데 이는 templates 폴더 아래에 있는
//		board/boardList.html을 의미합니다.
		ModelAndView mv = new ModelAndView("board/boardList");
		
		//게시글 목록을 조회합니다. 게시글 목록을 조회한다는 비즈니스 로직을 수행하기 위해서 보트 서비스 클래스의 셀렉트 보드리스트 메서드를 호출합니다.
		List<BoardDto> list = boardService.selectBoardList();

//		실행된 비즈니스 로직 결과 값을 뷰에 list라는 이름으로 저장합니다.뷰에서 사용할 경우 list라는 이름으로 조회결과를 사용할수 있습니다.
		mv.addObject("list",list);
		
		return mv;
	}
	
	//게시글 작성 컨트롤러 주소
	@RequestMapping(value ="/board/openBoardWrite.do")
	public String openBoardWirte() throws Exception{
		return "/board/boardWrite";
	}
	//글 등록
	@RequestMapping(value ="/board/insertBoard.do")//작성된 게글 등록하는 주소 앞에서 <form> action 속성에 지정된 insertBoard.do 확인할수 있습니다.
	public String insertBoard(BoardDto board) throws Exception{
			boardService.insertBoard(board); //사용자가 작성한 게시글을 저장하는 service영역의 메서드를 호출
		return "redirect:/board/openBoardList.do";//특정뷰를 호출 게시글 목록을 조회하는 
				
	}
	
	//글 상세보기
	@RequestMapping(value="/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception{
			ModelAndView mv = new ModelAndView("/board/boardDetail");
			
			BoardDto board = boardService.selectBoardDetail(boardIdx);
			mv.addObject("board",board);
			return mv;
	}
	//게시글 수정
	@RequestMapping(value="/board/updateBoard.do")
	public String updateBoard(BoardDto board)throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	//게시글 삭제
	@RequestMapping(value="/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
	
}
