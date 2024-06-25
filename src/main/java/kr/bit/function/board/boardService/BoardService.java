package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDTO.BoardDTO;
import kr.bit.function.board.boardDTO.PopupDTO;

import java.util.List;

public interface BoardService {

    //건내준 인자를 기반으로 데이터 삽입
    public void insert (BoardDTO boardDTO) throws Exception;

    //해당 게시물 번호에 맞는 게시물들 출력
    public BoardDTO selectOne(int festival_no) throws Exception;

    // 축제 데이터 전체 출력
    public List<BoardDTO> selectAll() throws Exception;
    // 팝업 데이터 전체 출력
    public List<PopupDTO> selectAllPopup() throws Exception;

    //해당 위치를 가진 데이터 전체 출력
    public List<BoardDTO> selectAllByLocation(String festival_location) throws Exception;

    //해당 게시글번호를 가진 데이터를 수정
    public void update (int festival_no, BoardDTO boardDTO) throws Exception;
    //게시물번호 기반으로 데이터삭제
    public void delete(int festival_no) throws Exception;

}