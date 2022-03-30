package board.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.board.dto.BoardDto;
import board.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	//경로 잡아주기
	@Autowired
	private BoardMapper boardMapper;
	//리스트 DTO에서 가져오기
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDto board) throws Exception {
		boardMapper.insertBoard(board);
		
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		boardMapper.updateHitCount(boardIdx);//선택된 게시글의 조회수를 증가시킵니다.
		
		
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);//게시글 내용 조회
		
		return board;
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
		
	}

	@Override
	public void updateBoard(BoardDto board) throws Exception {

		boardMapper.updateBoard(board);
	}
	
	

	
}
