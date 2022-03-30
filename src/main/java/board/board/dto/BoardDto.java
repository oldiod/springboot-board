package board.board.dto;


import java.time.LocalDateTime;
import lombok.Data;


/*DTO는 애플리케이션 내의 각 계층 간 데이터를 주고받는데 사용되는 객체입니다. 
 * 각 계층이란 3장에서 잠시 살펴본 뷰, 컨트롤러, 서비스, DAO 그리고 데이터베이스등을 의미합니다.*/
@Data
public class BoardDto {
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String creatorId;
	private	LocalDateTime createdDatetime;
	private String updaterId;
	private LocalDateTime updatedDatetime;
	
	
	
}