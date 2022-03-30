package board.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.board.dto.BoardDto;

@Mapper //mybatis 매퍼 인터페이스 선언
public interface BoardMapper {

	//인터페이스이기 떄문에 메서드의 이름과 반환 형식만 지정합니다. 
	//여기서 지정한 메서드의 이름은 잠시 후 나올 SQL의 이름과 동일해야 합니다.	
	
	//게시글 조회
		List<BoardDto> selectBoardList() throws Exception;

		
	//게시글 등록
		void insertBoard(BoardDto board) throws Exception;
	//게시글 조회수
		void updateHitCount(int boardIdx) throws Exception;
	//글 상세보기
		BoardDto selectBoardDetail(int boardIdx) throws Exception;
	//글 수정
		void updateBoard(BoardDto board) throws Exception;
	//글 삭제
		void deleteBoard(int boardIdx)throws Exception;
		
		
}
