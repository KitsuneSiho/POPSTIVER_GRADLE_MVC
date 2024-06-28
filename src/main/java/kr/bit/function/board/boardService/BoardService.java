package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDTO.*;

import java.util.List;

public interface BoardService {

    //=====================================================================================//
    //                               🎇🎇 FESTIVAL 축제 🎇🎇                               //
    //=====================================================================================//
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

    // 해당 게시글 번호를 가진 댓글 전체 출력
    public List<FestivalCommentDTO> selectFestivalComment(int festival_no) throws Exception;


    //=====================================================================================//
    //                            🎁🎁 POPUP  팝업스토어 🎁🎁                               //
    //=====================================================================================//
    public void insertPopupManual() throws Exception; //팝업 보드 디비에 데이터 삽입

    //건내준 인자를 기반으로 데이터 삽입
    public void insertPopup (PopupBoardDTO popupBoardDTO) throws Exception;

    //해당 게시물 번호에 맞는 게시물들 출력
    public PopupBoardDTO selectOnePopup(int popup_no) throws Exception;
    //데이터 전체 출력
    public List<PopupBoardDTO> selectAllPopup() throws Exception;
    //해당 위치를 가진 데이터 전체 출력
    public List<PopupBoardDTO> selectAllPopupByLocation(String popup_location) throws Exception;

    //해당 게시글번호를 가진 데이터를 수정
    public void updatePopup (int popup_no, PopupBoardDTO popupBoardDTO) throws Exception;
    //게시물번호 기반으로 데이터삭제
    public void deletePopup(int popup_no) throws Exception;

    // 해당 게시글 번호를 가진 댓글 전체 출력
    public List<PopupCommentDTO> selectPopupComment(int popup_no) throws Exception;

    //=====================================================================================//
    //                               📖📖 COMMUNITY 자유게시판 📖📖                         //
    //=====================================================================================//
    public void insertCommunity(CommunityDTO communityDTO) throws Exception;
    public List<CommunityDTO> selectAllCommunity() throws Exception;
    public CommunityDTO selectCommunityOne(int board_no) throws Exception;
    //=====================================================================================//
    //                              ⚠️⚠️ NOTICE  공지게시판 ⚠️⚠️                            //
    //=====================================================================================//
    public List<NoticeDTO> selectAllNotice() throws Exception;//공지출력
    public NoticeDTO selectNoticeOne(int notice_no) throws Exception;
    //=====================================================================================//
    //                          📢📢 BUSINESS  주최자등록게시판 📢📢                         //
    //=====================================================================================//
    public void insertBusiness(TemporaryPostDTO temporaryPostDTO) throws Exception;

    //=====================================================================================//
    //                             📤📤 REPORT  제보게시판 📤📤                             //
    //=====================================================================================//
    public void insertReport(ReportDTO reportDTO) throws Exception;
    public List<ReportDTO> selectReportAll() throws Exception;
    //=====================================================================================//
    //                            🧑‍🤝‍🧑🧑‍🤝‍🧑 COMPANION  동행게시판 🧑‍🤝‍🧑🧑‍🤝‍🧑                           //
    //=====================================================================================//



}