package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDAO.BoardRepository;
import kr.bit.function.board.boardDTO.CommunityDTO;
import kr.bit.function.board.boardDTO.FestivalBoardDTO;
import kr.bit.function.board.boardDTO.NoticeDTO;
import kr.bit.function.board.boardDTO.PopupBoardDTO;
import kr.bit.function.board.boardEntity.CommunityEntity;
import kr.bit.function.board.boardEntity.FestivalEntity;
import kr.bit.function.board.boardEntity.NoticeEntity;
import kr.bit.function.board.boardEntity.PopupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service 어노테이션으로 해당 클래스는 service의 역활을 하는 클래스임을 선언함
//이 클래스는 BoardService인터페이스를 상속 받음.
//그래서 BoardService에 정의된 메소드를 override해서 각 메소드를 재정의 해주어야 합니당.
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    //=====================================================================================//
    //                                      FESTIVAL                                       //
    //=====================================================================================//


    @Override
    public void insertFestivalManual() throws Exception{
        //자기 자신 클래스(this)의 insert 메소드를 실행한다.
        //insert()메소드에 들어갈 인자는 BoardDTO형태의 데이터이며
        //BoardDto에 넣을 때 new를 통해 이름,학번,각 성적 데이터를 가진 객체를 만들어서 넣는다.
        //이건 예시이고 JSP에서 게시글을 삽입할때 해당부분을 인자로 구현하면 편할것이다.

        this.insertFestival(new FestivalBoardDTO(0, "영등포페스티벌", "페스티벌에 어서 오셈", "서울시",
                "서울시","영등포구","구로동", "2024-07-01", "2024-07-18",
                "평일 오전9:00 ~ 오후4:00", null, "/resources/3412313.png",
                2, 567, 1039, "www.seoul.go.kr", "www.instagram.Seoul"));

        this.insertFestival(new FestivalBoardDTO(0, "한강불꽃축제", "국내최대불꽃축제!", "서울시",
                "서울시","송파구","잠실동", "2024-07-12", "2024-07-14",
                "오후10:00", null, "/resources/42246264.png",
                2, 2055, 32144, "www.seoul.go.kr", "www.instagram.Seoul"));

    }

    @Override
    public void insertFestival(FestivalBoardDTO festivalBoardDTO) throws Exception { //데이터삽입
        //Repository의 insert()메소드를 불러와서(DB요청)
        //BoardEntity형의 데이터를 인자로 넣는다.
        //BoardEntity형의 데이터를 넣을 때 new를 통해 객체를 만들고
        //넣을 데이터로서 매개변수로 받은 BoardDTO의 데이터를 get메소드를 통해
        //페스티벌 정보를 추출하여 데이터를 넣어준다
        boardRepository.insertFestivalRepo(new FestivalEntity(
                0,                       // festival_no는 NULL 값으로 설정
                festivalBoardDTO.getFestival_title(),
                festivalBoardDTO.getFestival_content(),
                festivalBoardDTO.getHost(),
                festivalBoardDTO.getFestival_dist(),
                festivalBoardDTO.getFestival_subdist(),
                festivalBoardDTO.getFestival_location(),
                festivalBoardDTO.getFestival_start(),
                festivalBoardDTO.getFestival_end(),
                festivalBoardDTO.getOpen_time(),
                null,                       // festival_post_date는 NULL 값으로 설정
                festivalBoardDTO.getFestival_attachment(),
                festivalBoardDTO.getEvent_type(),
                festivalBoardDTO.getLike_that(),
                festivalBoardDTO.getViews(),
                festivalBoardDTO.getBrand_link(),
                festivalBoardDTO.getBrand_sns()
        ));
    }

    @Override
    public FestivalBoardDTO selectOneFestival(int festival_no) throws Exception { //페스티벌 게시글 번호로 찾기
        //FestivalEntity형의 변수를 하나 만들고
        FestivalEntity festivalEntity = null;
        try {
            //레포지토리의 getRecordByFestivalNo()메소드를 불러와서(DB요청)
            //인자로 festival_no를 넣어서 해당하는 데이터를 boardEntity변수에 담는다.
            festivalEntity = boardRepository.getFestivalByFestivalNoRepo(festival_no);
        }catch(Exception e) {
            e.printStackTrace();
        }
        //담은 데이터를 다시 BoardDTO객체를 만들어서 각 데이터를 넣고  리턴한다.
        assert festivalEntity != null; //ㅈㅁ
        return new FestivalBoardDTO(festivalEntity.getFestival_no(), festivalEntity.getFestival_title(), festivalEntity.getFestival_content(), festivalEntity.getHost(), festivalEntity.getFestival_dist(), festivalEntity.getFestival_subdist(), festivalEntity.getFestival_location(),
                festivalEntity.getFestival_start(), festivalEntity.getFestival_end(), festivalEntity.getOpen_time(), festivalEntity.getFestival_post_date(),
                festivalEntity.getFestival_attachment(), festivalEntity.getEvent_type(), festivalEntity.getLike_that(), festivalEntity.getViews(), festivalEntity.getBrand_link(), festivalEntity.getBrand_sns());
    }

    @Override
    public List<FestivalBoardDTO> selectAllFestival() throws Exception { //전체데이터조회
        //List<BoardEntity>형태의 변수를 하나 만든다.
        List<FestivalEntity> boardEntities = null;
        try {
            //레파지토리의 getAllrecords()메소드를 불러와서(DB요청)
            //리턴된 데이터를 Entities에 담는다.
            boardEntities =boardRepository.getFestivalRepo();
        }catch(Exception e) {
            e.printStackTrace();
        }
        //List<BoardDTO>형의 변수를 하나 생성하고
        List<FestivalBoardDTO> festivalData = new ArrayList<FestivalBoardDTO>();
        //for문을 써서 list갯수 만큼 반복하면서,
        for(int i=0;i < boardEntities.size();i++) {
            //boardEntities에 담겼던 모든 데이터들을 다시 BoardDTO객체를 생성해서 거기에 담아 festivalData리스트에 담는다.
            festivalData.add(new FestivalBoardDTO(boardEntities.get(i).getFestival_no(),
                    boardEntities.get(i).getFestival_title(),
                    boardEntities.get(i).getFestival_content(),
                    boardEntities.get(i).getHost(),
                    boardEntities.get(i).getFestival_dist(),
                    boardEntities.get(i).getFestival_subdist(),
                    boardEntities.get(i).getFestival_location(),
                    boardEntities.get(i).getFestival_start(),
                    boardEntities.get(i).getFestival_end(),
                    boardEntities.get(i).getOpen_time(),
                    boardEntities.get(i).getFestival_post_date(),
                    boardEntities.get(i).getFestival_attachment(),
                    boardEntities.get(i).getEvent_type(),
                    boardEntities.get(i).getLike_that(),
                    boardEntities.get(i).getViews(),
                    boardEntities.get(i).getBrand_link(),
                    boardEntities.get(i).getBrand_sns()
            ));
        }
        //그렇게 담겨진 리스트를 리턴한다.
        return festivalData;
    }

    @Override
    public List<FestivalBoardDTO> selectAllFestivalByLocation(String festival_dist) throws Exception { //위치정보기반검색(시)
        return null;
    }

    @Override
    public void updateFestival(int fastival_no, FestivalBoardDTO festivalBoardDTO) throws Exception{
        //게시글 번호를 바탕으로 게시글 수정
    }

    @Override
    public void deleteFestival(int festival_no) throws Exception {
        //게시글 번호 바탕으로 게시글삭제
    }

    //=====================================================================================//
    //                                      POPUP                                          //
    //=====================================================================================//


    @Override
    public void insertPopupManual() throws Exception {
        // 자기 자신 클래스(this)의 insert 메소드를 실행한다.
        // insert() 메소드에 들어갈 인자는 PopupBoardDTO 형태의 데이터이며
        // PopupDto에 넣을 때 new를 통해 이름, 학번, 각 성적 데이터를 가진 객체를 만들어서 넣는다.
        // 이건 예시이고 JSP에서 게시글을 삽입할 때 해당 부분을 인자로 구현하면 편할 것이다.

        this.insertPopup(new PopupBoardDTO(0, "영등포팝업", "팝업에 어서 오셈", "서울시",
                "서울시", "영등포구", "구로동", "2024-07-01", "2024-07-18",
                "평일 오전9:00 ~ 오후4:00", null, "/resources/3412313.png",
                2, 567, 1039, "www.seoul.go.kr", "www.instagram.Seoul"));

        this.insertPopup(new PopupBoardDTO(0, "한강불꽃축제", "국내최대불꽃축제!", "서울시",
                "서울시", "송파구", "잠실동", "2024-07-12", "2024-07-14",
                "오후10:00", null, "/resources/42246264.png",
                2, 2055, 32144, "www.seoul.go.kr", "www.instagram.Seoul"));

    }

    @Override
    public void insertPopup(PopupBoardDTO popupBoardDTO) throws Exception { // 데이터삽입
        // Repository의 insert() 메소드를 불러와서(DB요청)
        // PopupEntity 형의 데이터를 인자로 넣는다.
        // PopupEntity 형의 데이터를 넣을 때 new를 통해 객체를 만들고
        // 넣을 데이터로서 매개변수로 받은 PopupDTO의 데이터를 get 메소드를 통해
        // 팝업 정보를 추출하여 데이터를 넣어준다
        boardRepository.insertPopupRepo(new PopupEntity(
                0,                       // popup_no는 NULL 값으로 설정
                popupBoardDTO.getPopup_title(),
                popupBoardDTO.getPopup_content(),
                popupBoardDTO.getHost(),
                popupBoardDTO.getPopup_dist(),
                popupBoardDTO.getPopup_subdist(),
                popupBoardDTO.getPopup_location(),
                popupBoardDTO.getPopup_start(),
                popupBoardDTO.getPopup_end(),
                popupBoardDTO.getOpen_time(),
                null,                       // popup_post_date는 NULL 값으로 설정
                popupBoardDTO.getPopup_attachment(),
                popupBoardDTO.getEvent_type(),
                popupBoardDTO.getLike_that(),
                popupBoardDTO.getViews(),
                popupBoardDTO.getBrand_link(),
                popupBoardDTO.getBrand_sns()
        ));
    }

    @Override
    public PopupBoardDTO selectOnePopup(int popup_no) throws Exception { // 팝업 게시글 번호로 찾기
        // PopupEntity 형의 변수를 하나 만들고
        PopupEntity popupEntity = null;
        try {
            // 레포지토리의 getRecordByPopupNo() 메소드를 불러와서(DB요청)
            // 인자로 popup_no를 넣어서 해당하는 데이터를 boardEntity 변수에 담는다.
            popupEntity = boardRepository.getPopupByPopupNoRepo(popup_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 담은 데이터를 다시 PopupDTO 객체를 만들어서 각 데이터를 넣고 리턴한다.
        assert popupEntity != null;
        return new PopupBoardDTO(popupEntity.getPopup_no(), popupEntity.getPopup_title(), popupEntity.getPopup_content(), popupEntity.getHost(), popupEntity.getPopup_dist(), popupEntity.getPopup_subdist(), popupEntity.getPopup_location(),
                popupEntity.getPopup_start(), popupEntity.getPopup_end(), popupEntity.getOpen_time(), popupEntity.getPopup_post_date(),
                popupEntity.getPopup_attachment(), popupEntity.getEvent_type(), popupEntity.getLike_that(), popupEntity.getViews(), popupEntity.getBrand_link(), popupEntity.getBrand_sns());
    }

    @Override
    public List<PopupBoardDTO> selectAllPopup() throws Exception { // 전체 데이터 조회
        // List<PopupEntity> 형태의 변수를 하나 만든다.
        List<PopupEntity> popupEntities = null;
        try {
            // 레포지토리의 getAllPopups() 메소드를 불러와서(DB요청)
            // 리턴된 데이터를 Entities에 담는다.
            popupEntities = boardRepository.getPopupRepo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // List<PopupDTO> 형의 변수를 하나 생성하고
        List<PopupBoardDTO> popupData = new ArrayList<>();
        // for문을 써서 list 갯수 만큼 반복하면서,
        for (int i = 0; i < popupEntities.size(); i++) {
            // popupEntities에 담겼던 모든 데이터들을 다시 PopupDTO 객체를 생성해서 거기에 담아 popupData 리스트에 담는다.
            popupData.add(new PopupBoardDTO(popupEntities.get(i).getPopup_no(),
                    popupEntities.get(i).getPopup_title(),
                    popupEntities.get(i).getPopup_content(),
                    popupEntities.get(i).getHost(),
                    popupEntities.get(i).getPopup_dist(),
                    popupEntities.get(i).getPopup_subdist(),
                    popupEntities.get(i).getPopup_location(),
                    popupEntities.get(i).getPopup_start(),
                    popupEntities.get(i).getPopup_end(),
                    popupEntities.get(i).getOpen_time(),
                    popupEntities.get(i).getPopup_post_date(),
                    popupEntities.get(i).getPopup_attachment(),
                    popupEntities.get(i).getEvent_type(),
                    popupEntities.get(i).getLike_that(),
                    popupEntities.get(i).getViews(),
                    popupEntities.get(i).getBrand_link(),
                    popupEntities.get(i).getBrand_sns()
            ));
        }
        // 그렇게 담겨진 리스트를 리턴한다.
        return popupData;
    }

    @Override
    public List<PopupBoardDTO> selectAllPopupByLocation(String popup_dist) throws Exception { // 위치 정보 기반 검색(시)
        return null;
    }

    @Override
    public void updatePopup(int popup_no, PopupBoardDTO popupBoardDTO) throws Exception {
        // 게시글 번호를 바탕으로 게시글 수정
    }

    @Override
    public void deletePopup(int popup_no) throws Exception {
        // 게시글 번호 바탕으로 게시글 삭제
    }



    //=====================================================================================//
    //                                      NOTICE                                         //
    //=====================================================================================//
    @Override
    public List<NoticeDTO> selectAllNotice() throws Exception {
        List<NoticeEntity> noticeEntities = null;
        try {
            // 레포지토리의 getAllPopups() 메소드를 불러와서(DB요청)
            // 리턴된 데이터를 Entities에 담는다.
            noticeEntities = boardRepository.getNoticeRepo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // List<PopupDTO> 형의 변수를 하나 생성하고
        List<NoticeDTO> noticeData = new ArrayList<>();
        // for문을 써서 list 갯수 만큼 반복하면서,
        for (int i = 0; i < noticeEntities.size(); i++) {
            // popupEntities에 담겼던 모든 데이터들을 다시 PopupDTO 객체를 생성해서 거기에 담아 popupData 리스트에 담는다.
            noticeData.add(new NoticeDTO(
                    noticeEntities.get(i).getNotice_no(),
                    noticeEntities.get(i).getNotice_title(),
                    noticeEntities.get(i).getNotice_content(),
                    noticeEntities.get(i).getNotice_date()

            ));
        }
        // 그렇게 담겨진 리스트를 리턴한다.
        return noticeData;
    }


    @Override
    public List<NoticeDTO> selectOneNotice(int notice_no) throws Exception {
        return List.of();
    }

    //=====================================================================================//
    //                                     COMMUNITY                                       //
    //=====================================================================================//
    public void insertCommunity (CommunityDTO communityDTO) throws Exception{

    }
}
