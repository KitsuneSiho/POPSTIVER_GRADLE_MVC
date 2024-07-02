package kr.bit.function.board.boardDAO;

import kr.bit.function.board.boardDTO.*;
import kr.bit.function.board.boardEntity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class BoardRepository {
    private static final Logger logger = LoggerFactory.getLogger(BoardRepository.class);
    private JdbcTemplate jdbcTemplate = null;

    //해당클래스의 로그를 가져옴
    @Autowired
    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //=====================================================================================//
    //                               🎇🎇 FESTIVAL 축제 🎇🎇                               //
    //=====================================================================================//

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
                festivalEntity.setFestival_tag1(rs.getString("festival_tag1"));
                festivalEntity.setFestival_tag2(rs.getString("festival_tag2"));
                festivalEntity.setFestival_tag3(rs.getString("festival_tag3"));
                festivalEntity.setFestival_tag4(rs.getString("festival_tag4"));
                festivalEntity.setFestival_tag5(rs.getString("festival_tag5"));

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
                        festivalEntity.setFestival_tag1(rs.getString("festival_tag1"));
                        festivalEntity.setFestival_tag2(rs.getString("festival_tag2"));
                        festivalEntity.setFestival_tag3(rs.getString("festival_tag3"));
                        festivalEntity.setFestival_tag4(rs.getString("festival_tag4"));
                        festivalEntity.setFestival_tag5(rs.getString("festival_tag5"));
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
                "insert into festival(festival_title, festival_content, host, festival_dist, festival_subdist, festival_location, festival_start, festival_end, open_time, festival_attachment, event_type, like_that, views, brand_link, brand_sns, festival_tag1,festival_tag2,festival_tag3,festival_tag4,festival_tag5) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
                , festivalEntity.getFestival_title(), festivalEntity.getFestival_content(), festivalEntity.getHost(), festivalEntity.getFestival_dist(), festivalEntity.getFestival_subdist(), festivalEntity.getFestival_location(), festivalEntity.getFestival_start(),
                festivalEntity.getFestival_end(), festivalEntity.getOpen_time(), festivalEntity.getFestival_attachment(), festivalEntity.getEvent_type(), festivalEntity.getLike_that(), festivalEntity.getViews(), festivalEntity.getBrand_link(), festivalEntity.getBrand_sns(),
                festivalEntity.getFestival_tag1(), festivalEntity.getFestival_tag1(),festivalEntity.getFestival_tag2(),festivalEntity.getFestival_tag3(),festivalEntity.getFestival_tag4(),festivalEntity.getFestival_tag5()
        );
    }

    public List<FestivalCommentEntity> getFestivalComments(int festival_no) throws Exception {
        List<FestivalCommentEntity> result = jdbcTemplate.query(
                "select * from festival_comment where festival_no=? ORDER BY comment_date DESC;", //쿼리문
                new RowMapper<FestivalCommentEntity>() {
                    @Override
                    public FestivalCommentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        FestivalCommentEntity festivalCommentEntity = new FestivalCommentEntity();
                        festivalCommentEntity.setComment_no(rs.getInt("comment_no"));
                        festivalCommentEntity.setEvent_type(rs.getInt("event_type"));
                        festivalCommentEntity.setComment_writer(rs.getString("comment_writer"));
                        festivalCommentEntity.setComment_date(rs.getString("comment_date"));
                        festivalCommentEntity.setVisit_date(rs.getString("visit_date"));
                        festivalCommentEntity.setComment_content(rs.getString("comment_content"));
                        festivalCommentEntity.setFestival_no(rs.getInt("festival_no"));
                        festivalCommentEntity.setComment_attachment(rs.getString("comment_attachment"));
                        festivalCommentEntity.setStar_rate(rs.getInt("star_rate"));

                        return festivalCommentEntity;
                    }
                    //쿼리끝 ? 부분에 데이터를 넣는다
                }, festival_no);
        //데이터를 담은 list를 반환 시 조건을 걸어서 조건에 맞게 보낸다
        //삼항 연산자로 isEmpty()인지 아닌지 판단해서 리턴하도록 한다!
        //empty면 null값이 반환되고, 아니면  results.get(0)이 반환된다.
        //즉, 메소드에서 정의한 리턴 형(BoardEntity )에 맞게 데이터가 리턴 될 수 있다.
        return result;
    }

    public void increaseFestivalViews(int festival_no) {
        String sql = "UPDATE festival SET views = views + 1 WHERE festival_no = ?";
        jdbcTemplate.update(sql, festival_no);
    }

    //=====================================================================================//
    //                            🎁🎁 POPUP  팝업스토어 🎁🎁                               //
    //=====================================================================================//

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
                popupEntity.setPopup_tag1(rs.getString("popup_tag1"));
                popupEntity.setPopup_tag2(rs.getString("popup_tag2"));
                popupEntity.setPopup_tag3(rs.getString("popup_tag3"));
                popupEntity.setPopup_tag4(rs.getString("popup_tag4"));
                popupEntity.setPopup_tag5(rs.getString("popup_tag5"));

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
                        popupEntity.setPopup_tag1(rs.getString("popup_tag1"));
                        popupEntity.setPopup_tag2(rs.getString("popup_tag2"));
                        popupEntity.setPopup_tag3(rs.getString("popup_tag3"));
                        popupEntity.setPopup_tag4(rs.getString("popup_tag4"));
                        popupEntity.setPopup_tag5(rs.getString("popup_tag5"));

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
                "insert into popup(popup_title, popup_content, host, popup_dist, popup_subdist, popup_location, popup_start, popup_end, open_time, popup_attachment, event_type, like_that, views, brand_link, brand_sns, popup_tag1, popup_tag2, popup_tag3,popup_tag4, popup_tag5) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
                , popupEntity.getPopup_title(), popupEntity.getPopup_content(), popupEntity.getHost(), popupEntity.getPopup_dist(), popupEntity.getPopup_subdist(), popupEntity.getPopup_location(), popupEntity.getPopup_start(),
                popupEntity.getPopup_end(), popupEntity.getOpen_time(), popupEntity.getPopup_attachment(), popupEntity.getEvent_type(), popupEntity.getLike_that(), popupEntity.getViews(), popupEntity.getBrand_link(), popupEntity.getBrand_sns(),
                popupEntity.getPopup_tag1(), popupEntity.getPopup_tag2(), popupEntity.getPopup_tag3(), popupEntity.getPopup_tag4(), popupEntity.getPopup_tag5()
        );
    }

    public List<PopupCommentEntity> getPopupComments(int popup_no) throws Exception {
        List<PopupCommentEntity> result = jdbcTemplate.query(
                "SELECT * FROM popup_comment WHERE popup_no = ? ORDER BY comment_date DESC;", //쿼리문
                new RowMapper<PopupCommentEntity>() {
                    @Override
                    public PopupCommentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PopupCommentEntity popupCommentEntity = new PopupCommentEntity();
                        popupCommentEntity.setComment_no(rs.getInt("comment_no"));
                        popupCommentEntity.setEvent_type(rs.getInt("event_type"));
                        popupCommentEntity.setComment_writer(rs.getString("comment_writer"));
                        popupCommentEntity.setComment_date(rs.getString("comment_date"));
                        popupCommentEntity.setVisit_date(rs.getString("visit_date"));
                        popupCommentEntity.setComment_content(rs.getString("comment_content"));
                        popupCommentEntity.setPopup_no(rs.getInt("popup_no"));
                        popupCommentEntity.setComment_attachment(rs.getString("comment_attachment"));
                        popupCommentEntity.setStar_rate(rs.getInt("star_rate"));

                        return popupCommentEntity;
                    }
                    //쿼리끝 ? 부분에 데이터를 넣는다
                }, popup_no);
        //데이터를 담은 list를 반환 시 조건을 걸어서 조건에 맞게 보낸다
        //삼항 연산자로 isEmpty()인지 아닌지 판단해서 리턴하도록 한다!
        //empty면 null값이 반환되고, 아니면  results.get(0)이 반환된다.
        //즉, 메소드에서 정의한 리턴 형(BoardEntity )에 맞게 데이터가 리턴 될 수 있다.
        return result;
    }

    public void increasePopupViews(int popup_no) {
        String sql = "UPDATE popup SET views = views + 1 WHERE popup_no = ?";
        jdbcTemplate.update(sql, popup_no);
    }

    //=====================================================================================//
    //                              ⚠️⚠️ NOTICE  공지게시판 ⚠️⚠️                            //
    //=====================================================================================//
    public List<NoticeEntity> getNoticeRepo() throws Exception {
        logger.info("getAllNotice"); //로그남기기
        //generic이  BoardEntity  인 List 를 선언하고 jdbc template 의 query 메소드를 통해서 전체 데이터를 추출하고 list에 담는다
        List<NoticeEntity> result = jdbcTemplate.query("select * from notice  ORDER BY notice_date DESC;",  new RowMapper<NoticeEntity>() {
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
    public NoticeEntity getNoticeOneRepo(int notice_no) throws Exception{
        List<NoticeEntity> result = jdbcTemplate.query(
                "select * from notice where notice_no=?;",
                new RowMapper<NoticeEntity>() {
                    @Override
                    public NoticeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        NoticeEntity noticeEntity = new NoticeEntity();
                        noticeEntity.setNotice_no(rs.getInt("notice_no"));
                        noticeEntity.setNotice_title(rs.getString("notice_title"));
                        noticeEntity.setNotice_content(rs.getString("notice_content"));
                        noticeEntity.setNotice_date(rs.getString("notice_date"));
                        return noticeEntity;
                    }
                },notice_no);
        return result.isEmpty() ? null : result.get(0);
    }

    //=====================================================================================//
    //                               📖📖 COMMUNITY 자유게시판 📖📖                         //
    //=====================================================================================//

    public List<CommunityEntity> getCommunityRepo() throws Exception {
        logger.info("getAllCommunity"); //로그남기기
        //generic이  BoardEntity  인 List 를 선언하고 jdbc template 의 query 메소드를 통해서 전체 데이터를 추출하고 list에 담는다
        List<CommunityEntity> result = jdbcTemplate.query("select * from community ORDER BY board_post_date DESC;",  new RowMapper<CommunityEntity>() {
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
                communityEntity.setBoard_attachment(rs.getString("board_attachment"));

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
    public CommunityEntity getCommunityOneRepo(int board_no) throws Exception{
        List<CommunityEntity> result = jdbcTemplate.query(
                "select * from community where board_no=?;",
                new RowMapper<CommunityEntity>() {
                    @Override
                    public CommunityEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        CommunityEntity communityEntity = new CommunityEntity();
                        communityEntity.setBoard_title(rs.getString("board_title"));
                        communityEntity.setBoard_content(rs.getString("board_content"));
                        communityEntity.setUser_name(rs.getNString("user_name"));
                        communityEntity.setBoard_attachment(rs.getString("board_attachment"));
                        communityEntity.setBoard_post_date(rs.getString("board_post_date"));
                        communityEntity.setBoard_views(rs.getInt("board_views"));
                        return communityEntity;
                    }
                },board_no);
        return result.isEmpty() ? null : result.get(0);
    }

    public void insertCommunityRepo(CommunityDTO communityDTO) {
        String sql = "INSERT INTO community (board_title, board_content, user_id, user_name, board_attachment, board_views) VALUES (?, ?, ?, ?, ?, ?)";
        // board_view 값은 일단 하드코딩으로 1로 지정
        jdbcTemplate.update(sql,
                communityDTO.getBoard_title(),
                communityDTO.getBoard_content(),
                communityDTO.getUser_id(),
                communityDTO.getUser_name(),
                communityDTO.getBoard_attachment(),
                communityDTO.getBoard_views()
                 );


    }

    public void increaseCommunityViews(int board_no) {
        String sql = "UPDATE community SET board_views = board_views + 1 WHERE board_no = ?";
        jdbcTemplate.update(sql, board_no);
    }
    //=====================================================================================//
    //                          📢📢 BUSINESS  주최자등록게시판 📢📢                         //
    //=====================================================================================//
    public void insertBusinessRepo(TemporaryPostDTO temporaryPostDTO) {
        String sql = "INSERT INTO temporary_post (temp_title, temp_content, temp_host, temp_location, temp_start, temp_end, event_type) VALUES (?,?,?,?,?,?,?)";
        // board_view 값은 일단 하드코딩으로 1로 지정
        jdbcTemplate.update(sql, temporaryPostDTO.getTemp_title(),
                temporaryPostDTO.getTemp_content(),
                temporaryPostDTO.getTemp_host(),
                temporaryPostDTO.getTemp_location(),
                temporaryPostDTO.getTemp_start(),
                temporaryPostDTO.getTemp_end(),
                temporaryPostDTO.getEvent_type()
        );
    }
    

    //=====================================================================================//
    //                             📤📤 REPORT  제보게시판 📤📤                             //
    //=====================================================================================//
    public void insertReportRepo(ReportDTO reportDTO) {
        String sql = "INSERT INTO report (report_title, report_content, report_host, report_location, report_start, report_end, brand_link, brand_sns,user_id, user_name, event_type) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, reportDTO.getReport_title(),
                reportDTO.getReport_content(),
                reportDTO.getReport_host(),
                reportDTO.getReport_location(),
                reportDTO.getReport_start(),
                reportDTO.getReport_end(),
                reportDTO.getBrand_link(),
                reportDTO.getBrand_sns(),
                reportDTO.getUser_id(),
                reportDTO.getUser_name(),
                reportDTO.getEvent_type()
        );
    }

    public List<ReportEntity> getReportRepo() throws Exception {
        //generic이  BoardEntity  인 List 를 선언하고 jdbc template 의 query 메소드를 통해서 전체 데이터를 추출하고 list에 담는다
        List<ReportEntity> result = jdbcTemplate.query("select * from report ORDER BY report_post_date DESC;",  new RowMapper<ReportEntity>() {
            //콤마 뒤에 RowMapper 객체를 만든다.
            //해당 객체에서 BoardEntity 형(u)을 반환하는 maprow메소드를 정의한다.(출력 데이터를 담는다)
            //그리고 해당 결과를 results에 담는다.
            @Override
            public ReportEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ReportEntity reportEntity = new ReportEntity();
                reportEntity.setReport_no(rs.getInt("report_no"));
                reportEntity.setReport_title(rs.getString("report_title"));
                reportEntity.setReport_content(rs.getString("report_content"));
                reportEntity.setReport_host(rs.getString("report_host"));
                reportEntity.setReport_dist(rs.getString("report_dist"));
                reportEntity.setReport_subdist(rs.getString("report_subdist"));
                reportEntity.setReport_location(rs.getString("report_location"));
                reportEntity.setReport_start(rs.getString("report_start"));
                reportEntity.setReport_end(rs.getString("report_end"));
                reportEntity.setOpen_time(rs.getString("open_time"));
                reportEntity.setReport_attachment(rs.getString("report_attachment"));
                reportEntity.setEvent_type(rs.getInt("event_type"));
                reportEntity.setBrand_link(rs.getString("brand_link"));
                reportEntity.setBrand_sns(rs.getString("brand_sns"));
                reportEntity.setReport_post_date(rs.getString("report_post_date"));
                reportEntity.setUser_id(rs.getString("user_id"));
                reportEntity.setUser_name(rs.getString("user_name"));
                return reportEntity;
            }
        });
        //데이터를 담은 List를 반환
        return result;
    }

    /*
     * 제보 게시물을 출력하기.
     * @param notice_no : 게시글번호(notice_no)
     * @return BoardEntity  형태의 데이터
     */
    public ReportEntity getReportOneRepo(int report_no) throws Exception{
        List<ReportEntity> result = jdbcTemplate.query(
                "select * from report where report_no=?;",
                new RowMapper<ReportEntity>() {
                    @Override
                    public ReportEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ReportEntity reportEntity = new ReportEntity();
                        reportEntity.setReport_no(rs.getInt("report_no"));
                        reportEntity.setReport_title(rs.getString("report_title"));
                        reportEntity.setReport_content(rs.getString("report_content"));
                        reportEntity.setReport_host(rs.getString("report_host"));
                        reportEntity.setReport_dist(rs.getString("report_dist"));
                        reportEntity.setReport_subdist(rs.getString("report_subdist"));
                        reportEntity.setReport_location(rs.getString("report_location"));
                        reportEntity.setReport_start(rs.getString("report_start"));
                        reportEntity.setReport_end(rs.getString("report_end"));
                        reportEntity.setOpen_time(rs.getString("open_time"));
                        reportEntity.setReport_attachment(rs.getString("report_attachment"));
                        reportEntity.setEvent_type(rs.getInt("event_type"));
                        reportEntity.setBrand_link(rs.getString("brand_link"));
                        reportEntity.setBrand_sns(rs.getString("brand_sns"));
                        reportEntity.setReport_post_date(rs.getString("report_post_date"));
                        reportEntity.setUser_id(rs.getString("user_id"));
                        reportEntity.setUser_name(rs.getString("user_name"));
                        return reportEntity;
                    }
                },report_no);
        return result.isEmpty() ? null : result.get(0);
    }

    //=====================================================================================//
    //                            🧑‍🤝‍🧑🧑‍🤝‍🧑 COMPANION  동행게시판 🧑‍🤝‍🧑🧑‍🤝‍🧑                           //
    //=====================================================================================//
    //동행데시판 모두 불러오기
    public List<CompanionEntity> getCompanionRepo() throws Exception {
        //generic이  BoardEntity  인 List 를 선언하고 jdbc template 의 query 메소드를 통해서 전체 데이터를 추출하고 list에 담는다
        List<CompanionEntity> result = jdbcTemplate.query("select * from companion ORDER BY comp_post_date DESC;",  new RowMapper<CompanionEntity>() {
            //콤마 뒤에 RowMapper 객체를 만든다.
            //해당 객체에서 BoardEntity 형(u)을 반환하는 maprow메소드를 정의한다.(출력 데이터를 담는다)
            //그리고 해당 결과를 results에 담는다.
            @Override
            public CompanionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                CompanionEntity companionEntity= new CompanionEntity();
                companionEntity.setComp_no(rs.getInt("comp_no"));
                companionEntity.setComp_title(rs.getString("comp_title"));
                companionEntity.setComp_content(rs.getString("comp_content"));
                companionEntity.setUser_name(rs.getString("user_name"));
                companionEntity.setUser_id(rs.getString("user_id"));
                companionEntity.setComp_date(rs.getString("comp_date"));
                companionEntity.setComp_link(rs.getString("comp_link"));
                companionEntity.setEvent_type(rs.getString("event_type"));
                companionEntity.setComp_post_date(rs.getString("comp_post_date"));
                companionEntity.setComp_views(rs.getInt("comp_views"));
                return companionEntity;
            }
        });
        //데이터를 담은 List를 반환
        return result;
    }

    public CompanionEntity getCompanionOneRepo(int comp_no) throws Exception{
        List<CompanionEntity> result = jdbcTemplate.query(
                "select * from companion where comp_no=?;",
                new RowMapper<CompanionEntity>() {
                    @Override
                    public CompanionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        CompanionEntity companionEntity = new CompanionEntity();
                        companionEntity.setComp_no(rs.getInt("comp_no"));
                        companionEntity.setComp_title(rs.getString("comp_title"));
                        companionEntity.setComp_content(rs.getString("comp_content"));
                        companionEntity.setUser_name(rs.getString("user_name"));
                        companionEntity.setUser_id(rs.getString("user_id"));
                        companionEntity.setComp_date(rs.getString("comp_date"));
                        companionEntity.setComp_link(rs.getString("comp_link"));
                        companionEntity.setEvent_type(rs.getString("event_type"));
                        companionEntity.setComp_post_date(rs.getString("comp_post_date"));
                        companionEntity.setComp_views(rs.getInt("comp_views"));
                        return companionEntity;
                    }
                },comp_no);
        return result.isEmpty() ? null : result.get(0);
    }

    public void insertCompanionRepo(CompanionDTO companionDTO) {
        String sql = "INSERT INTO companion (comp_title, comp_content, user_name, user_id, comp_date, comp_link,event_type, comp_views) VALUES (?,?,?,?,?,?,?,1)";
        jdbcTemplate.update(sql, companionDTO.getComp_title(),
                companionDTO.getComp_content(),
                companionDTO.getUser_name(),
                companionDTO.getUser_id(),
                companionDTO.getComp_date(),
                companionDTO.getComp_link(),
                companionDTO.getEvent_type()

        );
    }

    public void increaseCompanionViews(int comp_no) {
        String sql = "UPDATE companion SET comp_views = comp_views + 1 WHERE comp_no = ?";
        jdbcTemplate.update(sql, comp_no);
    }



    //=====================================================================================//
    //                            🧑‍🤝‍🧑🧑‍🤝‍🧑 축체 추천 게시판 🧑‍🤝‍🧑🧑‍🤝‍🧑                           //
    //=====================================================================================//

    public List<FestivalEntity> findFestivalsByTags(List<Integer> tags) {
        if (tags.isEmpty()) {
            return Collections.emptyList();
        }

        String sql = "SELECT * FROM festival WHERE festival_tag1 IN (" + joinTags(tags) + ") OR festival_tag2 IN (" + joinTags(tags) + ") OR festival_tag3 IN (" + joinTags(tags) + ") OR festival_tag4 IN (" + joinTags(tags) + ") OR festival_tag5 IN (" + joinTags(tags) + ")";

        return jdbcTemplate.query(sql, new RowMapper<FestivalEntity>() {
            @Override
            public FestivalEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                FestivalEntity festivalEntity = new FestivalEntity();
                festivalEntity.setFestival_no(rs.getInt("festival_no"));
                festivalEntity.setFestival_title(rs.getString("festival_title"));
                festivalEntity.setFestival_content(rs.getString("festival_content"));
                festivalEntity.setHost(rs.getString("host"));
                festivalEntity.setFestival_location(rs.getString("festival_location"));
                festivalEntity.setFestival_start(rs.getString("festival_start"));  // 날짜를 문자열로 가져옴
                festivalEntity.setFestival_end(rs.getString("festival_end"));
                festivalEntity.setOpen_time(rs.getString("open_time"));
                festivalEntity.setFestival_post_date(rs.getString("festival_post_date"));
                festivalEntity.setFestival_attachment(rs.getString("festival_attachment"));
                festivalEntity.setEvent_type(rs.getInt("event_type"));
                festivalEntity.setLike_that(rs.getInt("like_that"));
                festivalEntity.setViews(rs.getInt("views"));
                festivalEntity.setBrand_link(rs.getString("brand_link"));
                festivalEntity.setBrand_sns(rs.getString("brand_sns"));
                festivalEntity.setFestival_dist(rs.getString("festival_dist"));
                festivalEntity.setFestival_subdist(rs.getString("festival_subdist"));
                festivalEntity.setFestival_tag1(rs.getString("festival_tag1"));
                festivalEntity.setFestival_tag2(rs.getString("festival_tag2"));
                festivalEntity.setFestival_tag3(rs.getString("festival_tag3"));
                festivalEntity.setFestival_tag4(rs.getString("festival_tag4"));
                festivalEntity.setFestival_tag5(rs.getString("festival_tag5"));
                return festivalEntity;
            }
        });
    }

    public List<PopupEntity> findPopupsByTags(List<Integer> tags) {
        if (tags.isEmpty()) {
            return Collections.emptyList();
        }

        String sql = "SELECT * FROM popup WHERE popup_tag1 IN (" + joinTags(tags) + ") OR popup_tag2 IN (" + joinTags(tags) + ") OR popup_tag3 IN (" + joinTags(tags) + ") OR popup_tag4 IN (" + joinTags(tags) + ") OR popup_tag5 IN (" + joinTags(tags) + ")";

        return jdbcTemplate.query(sql, new RowMapper<PopupEntity>() {
            @Override
            public PopupEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                PopupEntity popupEntity = new PopupEntity();
                popupEntity.setPopup_no(rs.getInt("popup_no"));
                popupEntity.setPopup_title(rs.getString("popup_title"));
                popupEntity.setPopup_content(rs.getString("popup_content"));
                popupEntity.setHost(rs.getString("host"));
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
                popupEntity.setPopup_dist(rs.getString("popup_dist"));
                popupEntity.setPopup_subdist(rs.getString("popup_subdist"));
                popupEntity.setPopup_tag1(rs.getString("popup_tag1"));
                popupEntity.setPopup_tag2(rs.getString("popup_tag2"));
                popupEntity.setPopup_tag3(rs.getString("popup_tag3"));
                popupEntity.setPopup_tag4(rs.getString("popup_tag4"));
                popupEntity.setPopup_tag5(rs.getString("popup_tag5"));
                return popupEntity;
            }
        });
    }

    private String joinTags(List<Integer> tags) {
        return String.join(",", tags.stream().map(String::valueOf).toArray(String[]::new));
    }
}
//커뮤니티 DTO 연결, 상세페이지 연결, 제보파일 연결