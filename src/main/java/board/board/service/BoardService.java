package board.board.service;

import java.util.List;

import board.board.dto.BoardDto;

public interface BoardService {
	//리스트
	List<BoardDto> selectBoardList() throws Exception;
	//등록
	void insertBoard(BoardDto board) throws Exception;
	//글 상세보기
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	//글 삭제
	void deleteBoard(int boardIdx) throws Exception;
	//글 수정
	void updateBoard(BoardDto board) throws Exception;
}
