package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDAO.BoardRepository;
import kr.bit.function.board.boardDTO.FestivalBoardDTO;
import kr.bit.function.board.boardEntity.FestivalBoardEntity;
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

    @Override
    public void insertAllDB() throws Exception{
        //자기 자신 클래스(this)의 insert 메소드를 실행한다.
        //insert()메소드에 들어갈 인자는 BoardDTO형태의 데이터이며
        //BoardDto에 넣을 때 new를 통해 이름,학번,각 성적 데이터를 가진 객체를 만들어서 넣는다.
        //이건 예시이고 JSP에서 게시글을 삽입할때 해당부분을 인자로 구현하면 편할것이다.

        this.insert(new FestivalBoardDTO(0, "영등포페스티벌", "페스티벌에 어서 오셈", "서울시",
                "서울시","영등포구","구로동", "2024-07-01", "2024-07-18",
                "평일 오전9:00 ~ 오후4:00", null, "/resources/3412313.png",
                2, 567, 1039, "www.seoul.go.kr", "www.instagram.Seoul"));

        this.insert(new FestivalBoardDTO(0, "한강불꽃축제", "국내최대불꽃축제!", "서울시",
                "서울시","송파구","잠실동", "2024-07-12", "2024-07-14",
                "오후10:00", null, "/resources/42246264.png",
                2, 2055, 32144, "www.seoul.go.kr", "www.instagram.Seoul"));

    }

    @Override
    public void insert(FestivalBoardDTO festivalBoardDTO) throws Exception { //데이터삽입
        //Repository의 insert()메소드를 불러와서(DB요청)
        //BoardEntity형의 데이터를 인자로 넣는다.
        //BoardEntity형의 데이터를 넣을 때 new를 통해 객체를 만들고
        //넣을 데이터로서 매개변수로 받은 BoardDTO의 데이터를 get메소드를 통해
        //페스티벌 정보를 추출하여 데이터를 넣어준다
        boardRepository.insert(new FestivalBoardEntity(
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
    public FestivalBoardDTO selectOne(int festival_no) throws Exception { //페스티벌 게시글 번호로 찾기
        //ExamRIO형의 변수를 하나 만들고
        FestivalBoardEntity festivalBoardEntity = null;
        try {
            //레포지토리의 getRecordByFestivalNo()메소드를 불러와서(DB요청)
            //인자로 festival_no를 넣어서 해당하는 데이터를 boardEntity변수에 담는다.
            festivalBoardEntity = boardRepository.getRecordByFestivalNo(festival_no);
        }catch(Exception e) {
            e.printStackTrace();
        }
        //담은 데이터를 다시 BoardDTO객체를 만들어서 각 데이터를 넣고  리턴한다.
        assert festivalBoardEntity != null; //ㅈㅁ
        return new FestivalBoardDTO(festivalBoardEntity.getFestival_no(), festivalBoardEntity.getFestival_title(), festivalBoardEntity.getFestival_content(), festivalBoardEntity.getHost(), festivalBoardEntity.getFestival_dist(), festivalBoardEntity.getFestival_subdist(), festivalBoardEntity.getFestival_location(),
                festivalBoardEntity.getFestival_start(), festivalBoardEntity.getFestival_end(), festivalBoardEntity.getOpen_time(), festivalBoardEntity.getFestival_post_date(),
                festivalBoardEntity.getFestival_attachment(), festivalBoardEntity.getEvent_type(), festivalBoardEntity.getLike_that(), festivalBoardEntity.getViews(), festivalBoardEntity.getBrand_link(), festivalBoardEntity.getBrand_sns());
    }

    @Override
    public List<FestivalBoardDTO> selectAll() throws Exception { //전체데이터조회
        //List<BoardEntity>형태의 변수를 하나 만든다.
        List<FestivalBoardEntity> boardEntities = null;
        try {
            //레파지토리의 getAllrecords()메소드를 불러와서(DB요청)
            //리턴된 데이터를 Entities에 담는다.
            boardEntities =boardRepository.getAllrecords();
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
    public List<FestivalBoardDTO> selectAllByLocation(String festival_dist) throws Exception { //위치정보기반검색(시)
        return null;
    }

    @Override
    public void update(int fastival_no, FestivalBoardDTO festivalBoardDTO) throws Exception{
        //게시글 번호를 바탕으로 게시글 수정
    }

    @Override
    public void delete(int festival_no) throws Exception {
        //게시글 번호 바탕으로 게시글삭제
    }

}