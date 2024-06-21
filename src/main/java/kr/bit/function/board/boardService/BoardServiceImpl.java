package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDAO.BoardRepository;
import kr.bit.function.board.boardDTO.BoardDTO;
import kr.bit.function.board.boardEntity.BoardEntity;
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

        this.insert(new BoardDTO(0, "영등포페스티벌", "페스티벌에 어서 오셈", "서울시",
                "서울시 영등포구", "2024-07-01", "2024-07-18",
                "평일 오전9:00 ~ 오후4:00", null, "/resources/3412313.png",
                2, 567, 1039, "www.seoul.go.kr", "www.instagram.Seoul"));

        this.insert(new BoardDTO(0, "한강불꽃축제", "국내최대불꽃축제!", "서울시",
                "서울시 한강구", "2024-07-12", "2024-07-14",
                "오후10:00", null, "/resources/42246264.png",
                2, 2055, 32144, "www.seoul.go.kr", "www.instagram.Seoul"));

        this.insert(new BoardDTO(0, "동성로흠뻑쇼", "싸이와함꼐하는동성로워터밤", "대구",
                "대구광역시", "2024-08-04", "2024-08-11",
                "평일 오후10:00 <br> 주말 오후8:00", null, "/resources/daegu_waterbam.png",
                2, 3456, 21044, "www.daegu.world.com", "www.instagram.Daegu"));

    }

    @Override
    public void insert(BoardDTO boardDTO) throws Exception { //데이터삽입
        //Repository의 insert()메소드를 불러와서(DB요청)
        //BoardEntity형의 데이터를 인자로 넣는다.
        //BoardEntity형의 데이터를 넣을 때 new를 통해 객체를 만들고
        //넣을 데이터로서 매개변수로 받은 BoardDTO의 데이터를 get메소드를 통해
        //페스티벌 정보를 추출하여 데이터를 넣어준다
        boardRepository.insert(new BoardEntity(
                0,                       // festival_no는 NULL 값으로 설정
                boardDTO.getFestival_title(),
                boardDTO.getFestival_content(),
                boardDTO.getHost(),
                boardDTO.getFestival_location(),
                boardDTO.getFestival_start(),
                boardDTO.getFestival_end(),
                boardDTO.getOpen_time(),
                null,                       // festival_post_date는 NULL 값으로 설정
                boardDTO.getFestival_attachment(),
                boardDTO.getEvent_type(),
                boardDTO.getLike_that(),
                boardDTO.getViews(),
                boardDTO.getBrand_link(),
                boardDTO.getBrand_sns()
        ));
    }

    @Override
    public BoardDTO selectOne(int festival_no) throws Exception { //페스티벌 게시글 번호로 찾기
        //ExamRIO형의 변수를 하나 만들고
        BoardEntity boardEntity = null;
        try {
            //레포지토리의 getRecordByFestivalNo()메소드를 불러와서(DB요청)
            //인자로 festival_no를 넣어서 해당하는 데이터를 boardEntity변수에 담는다.
            boardEntity = boardRepository.getRecordByFestivalNo(festival_no);
        }catch(Exception e) {
            e.printStackTrace();
        }
        //담은 데이터를 다시 BoardDTO객체를 만들어서 각 데이터를 넣고  리턴한다.
        assert boardEntity != null; //ㅈㅁ
        return new BoardDTO(boardEntity.getFestival_no(), boardEntity.getFestival_title(), boardEntity.getFestival_content(), boardEntity.getHost(), boardEntity.getFestival_location(),
                boardEntity.getFestival_start(), boardEntity.getFestival_end(), boardEntity.getOpen_time(), boardEntity.getFestival_post_date(),
                boardEntity.getFestival_attachment(), boardEntity.getEvent_type(), boardEntity.getLike_that(), boardEntity.getViews(), boardEntity.getBrand_link(), boardEntity.getBrand_sns());
    }

    @Override
    public List<BoardDTO> selectAll() throws Exception { //전체데이터조회
        //List<BoardEntity>형태의 변수를 하나 만든다.
        List<BoardEntity> boardEntities = null;
        try {
            //레파지토리의 getAllrecords()메소드를 불러와서(DB요청)
            //리턴된 데이터를 Entities에 담는다.
            boardEntities =boardRepository.getAllrecords();
        }catch(Exception e) {
            e.printStackTrace();
        }
        //List<BoardDTO>형의 변수를 하나 생성하고
        List<BoardDTO> festivalData = new ArrayList<BoardDTO>();
        //for문을 써서 list갯수 만큼 반복하면서,
        for(int i=0;i < boardEntities.size();i++) {
            //boardEntities에 담겼던 모든 데이터들을 다시 BoardDTO객체를 생성해서 거기에 담아 festivalData리스트에 담는다.
            festivalData.add(new BoardDTO(boardEntities.get(i).getFestival_no(),
                    boardEntities.get(i).getFestival_title(),
                    boardEntities.get(i).getFestival_content(),
                    boardEntities.get(i).getHost(),
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
    public List<BoardDTO> selectAllByLocation(String festival_location) throws Exception { //위치정보기반검색
        return null;
    }

    @Override
    public void update(int fastival_no, BoardDTO boardDTO) throws Exception{
        //게시글 번호를 바탕으로 게시글 수정
    }

    @Override
    public void delete(int festival_no) throws Exception {
        //게시글 번호 바탕으로 게시글삭제
    }

}