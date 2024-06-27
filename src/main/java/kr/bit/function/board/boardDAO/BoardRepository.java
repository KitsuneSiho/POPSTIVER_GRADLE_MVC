package kr.bit.function.board.boardDAO;

import kr.bit.function.board.boardDTO.CommunityDTO;
import kr.bit.function.board.boardDTO.NoticeDTO;
import kr.bit.function.board.boardEntity.CommunityEntity;
import kr.bit.function.board.boardEntity.FestivalEntity;
import kr.bit.function.board.boardEntity.NoticeEntity;
import kr.bit.function.board.boardEntity.PopupEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardRepository {
    private static final Logger logger = LoggerFactory.getLogger(BoardRepository.class);
    private final JdbcTemplate jdbcTemplate;

    //해당클래스의 로그를 가져옴
    @Autowired
    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //-----------------FESTIVAL TABLE-------------------------------------//

    //festival 테이블 출력
    public List<FestivalEntity> getFestivalRepo() throws Exception {
        logger.info("getAllrecords"); //로그남기기
        //generic이  BoardEntity  인 List 를 선언하고 jdbc template 의 query 메소드를 통해서 전체 데이터를 추출하고 list에 담는다
        List<FestivalEntity> result = jdbcTemplate.query("select * from festival;",  new RowMapper<FestivalEntity>() {
            //콤마 뒤에 RowMapper 객체를 만든다.
            //해당 객체에서 BoardEntity 형(u)을 반환하는 maprow메소드를 정의한다.(출력 데이터를 담는다)
            //그리고 해당 결과를 results에 담는다.
            @Override
            public FestivalEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                FestivalEntity festivalEntity = new FestivalEntity();
                festivalEntity.setFestival_no(rs.getInt("festival_no"));
                festivalEntity.setFestival_title(rs.getString("festival_title"));
                festivalEntity.setFestival_content(rs.getString("festival_content"));
                festivalEntity.setHost(rs.getString("host"));
                festivalEntity.setFestival_dist(rs.getString("festival_dist"));
                festivalEntity.setFestival_subdist(rs.getString("festival_subdist"));
                festivalEntity.setFestival_location(rs.getString("festival_location"));
                festivalEntity.setFestival_start(rs.getString("festival_start"));
                festivalEntity.setFestival_end(rs.getString("festival_end"));
                festivalEntity.setOpen_time(rs.getString("open_time"));
                festivalEntity.setFestival_post_date(rs.getString("festival_post_date"));
                festivalEntity.setFestival_attachment(rs.getString("festival_attachment"));
                festivalEntity.setEvent_type(rs.getInt("event_type"));
                festivalEntity.setLike_that(rs.getInt("like_that"));
                festivalEntity.setViews(rs.getInt("views"));
                festivalEntity.setBrand_link(rs.getString("brand_link"));
                festivalEntity.setBrand_sns(rs.getString("brand_sns"));
                return festivalEntity;
            }
        });
        //데이터를 담은 List를 반환
        return result;
    }
    /*
     * 특정 게시물을 출력하기.
     * @param festival_no : 게시글번호(festival_no)
     * @return BoardEntity  형태의 데이터
     */
    public FestivalEntity getFestivalByFestivalNoRepo(int festival_no) throws Exception {
        //generic이 BoardEntity 인 list를 선언하고  jdbc template 실행결과를 담는다.
        //특이한 점은 데이터를 하나 뽑는데도 list로 선언한다.
        //전체 출력 메소드와 매우 흡사하다
        List<FestivalEntity> result = jdbcTemplate.query(
                "select * from festival where festival_no=?;", //쿼리문
                new RowMapper<FestivalEntity>() {
                    @Override
                    public FestivalEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        FestivalEntity festivalEntity = new FestivalEntity();
                        festivalEntity.setFestival_no(rs.getInt("festival_no"));
                        festivalEntity.setFestival_title(rs.getString("festival_title"));
                        festivalEntity.setFestival_content(rs.getString("festival_content"));
                        festivalEntity.setHost(rs.getString("host"));
                        festivalEntity.setFestival_dist(rs.getString("festival_dist"));
                        festivalEntity.setFestival_subdist(rs.getString("festival_subdist"));
                        festivalEntity.setFestival_location(rs.getString("festival_location"));
                        festivalEntity.setFestival_start(rs.getString("festival_start"));
                        festivalEntity.setFestival_end(rs.getString("festival_end"));
                        festivalEntity.setOpen_time(rs.getString("open_time"));
                        festivalEntity.setFestival_post_date(rs.getString("festival_post_date"));
                        festivalEntity.setFestival_attachment(rs.getString("festival_attachment"));
                        festivalEntity.setEvent_type(rs.getInt("event_type"));
                        festivalEntity.setLike_that(rs.getInt("like_that"));
                        festivalEntity.setViews(rs.getInt("views"));
                        festivalEntity.setBrand_link(rs.getString("brand_link"));
                        festivalEntity.setBrand_sns(rs.getString("brand_sns"));
                        return festivalEntity;
                    }
                    //쿼리끝 ? 부분에 데이터를 넣는다
                }, festival_no);
        //데이터를 담은 list를 반환 시 조건을 걸어서 조건에 맞게 보낸다
        //삼항 연산자로 isEmpty()인지 아닌지 판단해서 리턴하도록 한다!
        //empty면 null값이 반환되고, 아니면  results.get(0)이 반환된다.
        //즉, 메소드에서 정의한 리턴 형(BoardEntity )에 맞게 데이터가 리턴 될 수 있다.
        return result.isEmpty() ? null : result.get(0);

    }
    /*
     * 데이터를 insert하는 메소드
     * @param festivalEntity: ExanRIO형의 데이터(DB에 삽입하고자 하는 데이터)
     * @return int형인 status를 반환한다.(아마 성공 여부인듯 하다)
     */
    public int insertFestivalRepo(FestivalEntity festivalEntity) throws Exception{
        //메소드 불러올 때 로그를 남김
        logger.info("Board insert");
        //쿼리문을 적고 실행하고 리턴한다.(insert는 update)
        //sql : 데이터들을 넣음
        //insert 시  update메소드 안에 쿼리문을 적고 쿼리문에서 넣고자 하는 데이터는 ?로 처리한다 (preparedstatement 형식)
        //그리고 , 쿼리문 뒤에 ?에 해당하는 데이터를 적어준다.(template 형식)
        //BoardEntity 형의 객체에 들어있는 데이터를 get메소드로 가져온다.
        return jdbcTemplate.update(
                "insert into festival(festival_title, festival_content, host, festival_dist, festival_subdist, festival_location, festival_start, festival_end, open_time, festival_attachment, event_type, like_that, views, brand_link, brand_sns) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
                , festivalEntity.getFestival_title(), festivalEntity.getFestival_content(), festivalEntity.getHost(), festivalEntity.getFestival_dist(), festivalEntity.getFestival_subdist(), festivalEntity.getFestival_location(), festivalEntity.getFestival_start(),
                festivalEntity.getFestival_end(), festivalEntity.getOpen_time(), festivalEntity.getFestival_attachment(), festivalEntity.getEvent_type(), festivalEntity.getLike_that(), festivalEntity.getViews(), festivalEntity.getBrand_link(), festivalEntity.getBrand_sns());
    }

    //----------------------------POPUP TABLE--------------------------------------//

    //popup 테이블 출력
    public List<PopupEntity> getPopupRepo() throws Exception {
        logger.info("getAllPopup"); //로그남기기
        //generic이  BoardEntity  인 List 를 선언하고 jdbc template 의 query 메소드를 통해서 전체 데이터를 추출하고 list에 담는다
        List<PopupEntity> result = jdbcTemplate.query("select * from popup;",  new RowMapper<PopupEntity>() {
            //콤마 뒤에 RowMapper 객체를 만든다.
            //해당 객체에서 BoardEntity 형(u)을 반환하는 maprow메소드를 정의한다.(출력 데이터를 담는다)
            //그리고 해당 결과를 results에 담는다.
            @Override
            public PopupEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                PopupEntity popupEntity = new PopupEntity();
                popupEntity.setPopup_no(rs.getInt("popup_no"));
                popupEntity.setPopup_title(rs.getString("popup_title"));
                popupEntity.setPopup_content(rs.getString("popup_content"));
                popupEntity.setHost(rs.getString("host"));
                popupEntity.setPopup_dist(rs.getString("popup_dist"));
                popupEntity.setPopup_subdist(rs.getString("popup_subdist"));
                popupEntity.setPopup_location(rs.getString("popup_location"));
                popupEntity.setPopup_start(rs.getString("popup_start"));
                popupEntity.setPopup_end(rs.getString("popup_end"));
                popupEntity.setOpen_time(rs.getString("open_time"));
                popupEntity.setPopup_post_date(rs.getString("popup_post_date"));
                popupEntity.setPopup_attachment(rs.getString("popup_attachment"));
                popupEntity.setEvent_type(rs.getInt("event_type"));
                popupEntity.setLike_that(rs.getInt("like_that"));
                popupEntity.setViews(rs.getInt("views"));
                popupEntity.setBrand_link(rs.getString("brand_link"));
                popupEntity.setBrand_sns(rs.getString("brand_sns"));
                return popupEntity;
            }
        });
        //데이터를 담은 List를 반환
        return result;
    }
    /*
     * 특정 게시물을 출력하기.
     * @param popup_no : 게시글번호(popup_no)
     * @return BoardEntity  형태의 데이터
     */
    public PopupEntity getPopupByPopupNoRepo(int popup_no) throws Exception {
        //generic이 BoardEntity 인 list를 선언하고  jdbc template 실행결과를 담는다.
        //특이한 점은 데이터를 하나 뽑는데도 list로 선언한다.
        //전체 출력 메소드와 매우 흡사하다
        List<PopupEntity> result = jdbcTemplate.query(
                "select * from popup where popup_no=?;", //쿼리문
                new RowMapper<PopupEntity>() {
                    @Override
                    public PopupEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PopupEntity popupEntity = new PopupEntity();
                        popupEntity.setPopup_no(rs.getInt("popup_no"));
                        popupEntity.setPopup_title(rs.getString("popup_title"));
                        popupEntity.setPopup_content(rs.getString("popup_content"));
                        popupEntity.setHost(rs.getString("host"));
                        popupEntity.setPopup_dist(rs.getString("popup_dist"));
                        popupEntity.setPopup_subdist(rs.getString("popup_subdist"));
                        popupEntity.setPopup_location(rs.getString("popup_location"));
                        popupEntity.setPopup_start(rs.getString("popup_start"));
                        popupEntity.setPopup_end(rs.getString("popup_end"));
                        popupEntity.setOpen_time(rs.getString("open_time"));
                        popupEntity.setPopup_post_date(rs.getString("popup_post_date"));
                        popupEntity.setPopup_attachment(rs.getString("popup_attachment"));
                        popupEntity.setEvent_type(rs.getInt("event_type"));
                        popupEntity.setLike_that(rs.getInt("like_that"));
                        popupEntity.setViews(rs.getInt("views"));
                        popupEntity.setBrand_link(rs.getString("brand_link"));
                        popupEntity.setBrand_sns(rs.getString("brand_sns"));
                        return popupEntity;
                    }
                    //쿼리끝 ? 부분에 데이터를 넣는다
                }, popup_no);
        //데이터를 담은 list를 반환 시 조건을 걸어서 조건에 맞게 보낸다
        //삼항 연산자로 isEmpty()인지 아닌지 판단해서 리턴하도록 한다!
        //empty면 null값이 반환되고, 아니면  results.get(0)이 반환된다.
        //즉, 메소드에서 정의한 리턴 형(BoardEntity )에 맞게 데이터가 리턴 될 수 있다.
        return result.isEmpty() ? null : result.get(0);

    }
    /*
     * 데이터를 insert하는 메소드
     * @param festivalEntity: ExanRIO형의 데이터(DB에 삽입하고자 하는 데이터)
     * @return int형인 status를 반환한다.(아마 성공 여부인듯 하다)
     */
    public int insertPopupRepo(PopupEntity popupEntity) throws Exception{
        //메소드 불러올 때 로그를 남김
        logger.info("Popup Board insert");
        //쿼리문을 적고 실행하고 리턴한다.(insert는 update)
        //sql : 데이터들을 넣음
        //insert 시  update메소드 안에 쿼리문을 적고 쿼리문에서 넣고자 하는 데이터는 ?로 처리한다 (preparedstatement 형식)
        //그리고 , 쿼리문 뒤에 ?에 해당하는 데이터를 적어준다.(template 형식)
        //BoardEntity 형의 객체에 들어있는 데이터를 get메소드로 가져온다.
        return jdbcTemplate.update(
                "insert into popup(popup_title, popup_content, host, popup_dist, popup_subdist, popup_location, popup_start, popup_end, open_time, popup_attachment, event_type, like_that, views, brand_link, brand_sns) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
                , popupEntity.getPopup_title(), popupEntity.getPopup_content(), popupEntity.getHost(), popupEntity.getPopup_dist(), popupEntity.getPopup_subdist(), popupEntity.getPopup_location(), popupEntity.getPopup_start(),
                popupEntity.getPopup_end(), popupEntity.getOpen_time(), popupEntity.getPopup_attachment(), popupEntity.getEvent_type(), popupEntity.getLike_that(), popupEntity.getViews(), popupEntity.getBrand_link(), popupEntity.getBrand_sns());
    }


    //=====================================================================================//
    //                                 NOTICE  공지게시판                                    //
    //=====================================================================================//
    public List<NoticeEntity> getNoticeRepo() throws Exception {
        logger.info("getAllNotice"); //로그남기기
        //generic이  BoardEntity  인 List 를 선언하고 jdbc template 의 query 메소드를 통해서 전체 데이터를 추출하고 list에 담는다
        List<NoticeEntity> result = jdbcTemplate.query("select * from notice;",  new RowMapper<NoticeEntity>() {
            //콤마 뒤에 RowMapper 객체를 만든다.
            //해당 객체에서 BoardEntity 형(u)을 반환하는 maprow메소드를 정의한다.(출력 데이터를 담는다)
            //그리고 해당 결과를 results에 담는다.
            @Override
            public NoticeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeEntity noticeEntity = new NoticeEntity();
                noticeEntity.setNotice_no(rs.getInt("notice_no"));
                noticeEntity.setNotice_title(rs.getString("notice_title"));
                noticeEntity.setNotice_content(rs.getString("notice_content"));
                noticeEntity.setNotice_date(rs.getString("notice_date"));
                return noticeEntity;
            }
        });
        //데이터를 담은 List를 반환
        return result;
    }
    /*
     * 특정 게시물을 출력하기.
     * @param notice_no : 게시글번호(notice_no)
     * @return BoardEntity  형태의 데이터
     */
    public List<NoticeDTO> getNoticeOneRepo(int notice_no) throws Exception {
        logger.info("getOneNotice"); //로그남기기
        //generic이  BoardEntity  인 List 를 선언하고 jdbc template 의 query 메소드를 통해서 전체 데이터를 추출하고 list에 담는다
        List<NoticeDTO> result = jdbcTemplate.query("select * from notice where notice_no=?;",  new RowMapper<NoticeDTO>() {
            //콤마 뒤에 RowMapper 객체를 만든다.
            //해당 객체에서 BoardEntity 형(u)을 반환하는 maprow메소드를 정의한다.(출력 데이터를 담는다)
            //그리고 해당 결과를 results에 담는다.
            @Override
            public NoticeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeDTO noticeDTO = new NoticeDTO();
                noticeDTO.setNotice_no(rs.getInt("notice_no"));
                noticeDTO.setNotice_title(rs.getString("notice_title"));
                noticeDTO.setNotice_content(rs.getString("notice_content"));
                noticeDTO.setNotice_date(rs.getString("notice_date"));
                return noticeDTO;
            }
        },notice_no);
        //데이터를 담은 List를 반환
        return result;
    }

    //======================================================//
    //                    COMMUNITY 자유게시판                //
    //======================================================//

    public List<CommunityEntity> getCommunityRepo() throws Exception {
        logger.info("getAllCommunity"); //로그남기기
        //generic이  BoardEntity  인 List 를 선언하고 jdbc template 의 query 메소드를 통해서 전체 데이터를 추출하고 list에 담는다
        List<CommunityEntity> result = jdbcTemplate.query("select * from community;",  new RowMapper<CommunityEntity>() {
            //콤마 뒤에 RowMapper 객체를 만든다.
            //해당 객체에서 BoardEntity 형(u)을 반환하는 maprow메소드를 정의한다.(출력 데이터를 담는다)
            //그리고 해당 결과를 results에 담는다.
            @Override
            public CommunityEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                CommunityEntity communityEntity = new CommunityEntity();
                communityEntity.setBoard_no(rs.getInt("board_no"));
                communityEntity.setBoard_title(rs.getString("board_title"));
                communityEntity.setBoard_content(rs.getString("board_content"));
                communityEntity.setUser_id(rs.getString("user_id"));
                communityEntity.setUser_name(rs.getString("user_name"));
                communityEntity.setBoard_post_date(rs.getString("board_post_date"));
                communityEntity.setBoard_views(rs.getInt("board_views"));
                communityEntity.setBoard_attachment(rs.getBytes("board_attachment"));

                return communityEntity;
            }
        });
        //데이터를 담은 List를 반환
        return result;
    }
    /*
     * 특정 게시물을 출력하기.
     * @param notice_no : 게시글번호(notice_no)
     * @return BoardEntity  형태의 데이터
     */
    public CommunityEntity getCommunityByCommunityNoRepo(int board_no) throws Exception {
        //generic이 BoardEntity 인 list를 선언하고  jdbc template 실행결과를 담는다.
        //특이한 점은 데이터를 하나 뽑는데도 list로 선언한다.
        //전체 출력 메소드와 매우 흡사하다
        List<CommunityEntity> result = jdbcTemplate.query(
                "select * from community where board_no=?;", //쿼리문
                new RowMapper<CommunityEntity>() {
                    @Override
                    public CommunityEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        CommunityEntity communityEntity = new CommunityEntity();
                        communityEntity.setBoard_no(rs.getInt("board_no"));
                        communityEntity.setBoard_title(rs.getString("board_title"));
                        communityEntity.setBoard_content(rs.getString("board_content"));
                        communityEntity.setUser_id(rs.getString("user_id"));
                        communityEntity.setUser_name(rs.getString("user_name"));
                        communityEntity.setBoard_post_date(rs.getString("board_post_date"));
                        communityEntity.setBoard_views(rs.getInt("board_views"));
                        communityEntity.setBoard_attachment(rs.getBytes("board_attachment"));

                        return communityEntity;
                    }
                    //쿼리끝 ? 부분에 데이터를 넣는다
                }, board_no);
        //데이터를 담은 list를 반환 시 조건을 걸어서 조건에 맞게 보낸다
        //삼항 연산자로 isEmpty()인지 아닌지 판단해서 리턴하도록 한다!
        //empty면 null값이 반환되고, 아니면  results.get(0)이 반환된다.
        //즉, 메소드에서 정의한 리턴 형(BoardEntity )에 맞게 데이터가 리턴 될 수 있다.
        return result.isEmpty() ? null : result.get(0);

    }
    public void insertCommunityRepo(CommunityDTO communityDTO) {
        String sql = "INSERT INTO community (board_title, board_content, user_id, user_name) VALUES (?, ?, ?, ?)";
        // board_view 값은 일단 하드코딩으로 1로 지정
        jdbcTemplate.update(sql, communityDTO.getBoard_title(),
                communityDTO.getBoard_content(),
                communityDTO.getUser_id(),
                communityDTO.getUser_name()
                 );
    }

}