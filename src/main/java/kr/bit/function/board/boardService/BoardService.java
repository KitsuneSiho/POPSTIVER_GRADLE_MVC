package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDTO.FestivalBoardDTO;

import java.util.List;

public interface BoardService {

    //--------------FSTIVAL BOARD-----------------------------------------------------------//
    public void insertFestivalManual() throws Exception; //페스티벌 보드 디비에 데이터 삽입

    //건내준 인자를 기반으로 데이터 삽입
    public void insertFestival (FestivalBoardDTO festivalBoardDTO) throws Exception;

    //해당 게시물 번호에 맞는 게시물들 출력
    public FestivalBoardDTO selectOneFestival(int festival_no) throws Exception;
    //데이터 전체 출력
    public List<FestivalBoardDTO> selectAllFestival() throws Exception;
    //해당 위치를 가진 데이터 전체 출력
    public List<FestivalBoardDTO> selectAllFestivalByLocation(String festival_dist) throws Exception;

    //해당 게시글번호를 가진 데이터를 수정
    public void updateFestival (int festival_no, FestivalBoardDTO festivalBoardDTO) throws Exception;
    //게시물번호 기반으로 데이터삭제
    public void deleteFestival(int festival_no) throws Exception;

    //----------------POPUP BOARD----------------------------------------------------------------//
    public void insertPopupManual() throws Exception; //페스티벌 보드 디비에 데이터 삽입

    //건내준 인자를 기반으로 데이터 삽입
    public void insert (FestivalBoardDTO festivalBoardDTO) throws Exception;

    //해당 게시물 번호에 맞는 게시물들 출력
    public FestivalBoardDTO selectOne(int festival_no) throws Exception;
    //데이터 전체 출력
    public List<FestivalBoardDTO> selectAll() throws Exception;
    //해당 위치를 가진 데이터 전체 출력
    public List<FestivalBoardDTO> selectAllByLocation(String festival_location) throws Exception;

    //해당 게시글번호를 가진 데이터를 수정
    public void update (int festival_no, FestivalBoardDTO festivalBoardDTO) throws Exception;
    //게시물번호 기반으로 데이터삭제
    public void delete(int festival_no) throws Exception;

}