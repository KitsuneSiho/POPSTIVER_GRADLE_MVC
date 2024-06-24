package kr.bit.function.board.boardDAO;

import kr.bit.function.board.boardEntity.FestivalBoardEntity;
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
    //해당클래스의 로그를 가져옴
    @Autowired
    JdbcTemplate jdbcTemplate;

    //-----------------FESTIVAL TABLE-------------------------------------//

    //festival 테이블 출력
    public List<FestivalBoardEntity> getAllrecords() throws Exception {
        logger.info("getAllrecords"); //로그남기기
        //generic이  BoardEntity  인 List 를 선언하고 jdbc template 의 query 메소드를 통해서 전체 데이터를 추출하고 list에 담는다
        List<FestivalBoardEntity> result = jdbcTemplate.query("select * from festival;",  new RowMapper<FestivalBoardEntity>() {
            //콤마 뒤에 RowMapper 객체를 만든다.
            //해당 객체에서 BoardEntity 형(u)을 반환하는 maprow메소드를 정의한다.(출력 데이터를 담는다)
            //그리고 해당 결과를 results에 담는다.
            @Override
            public FestivalBoardEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                FestivalBoardEntity festivalBoardEntity = new FestivalBoardEntity();
                festivalBoardEntity.setFestival_no(rs.getInt("festival_no"));
                festivalBoardEntity.setFestival_title(rs.getString("festival_title"));
                festivalBoardEntity.setFestival_content(rs.getString("festival_content"));
                festivalBoardEntity.setHost(rs.getString("host"));
                festivalBoardEntity.setFestival_dist(rs.getString("festival_dist"));
                festivalBoardEntity.setFestival_subdist(rs.getString("festival_subdist"));
                festivalBoardEntity.setFestival_location(rs.getString("festival_location"));
                festivalBoardEntity.setFestival_start(rs.getString("festival_start"));
                festivalBoardEntity.setFestival_end(rs.getString("festival_end"));
                festivalBoardEntity.setOpen_time(rs.getString("open_time"));
                festivalBoardEntity.setFestival_post_date(rs.getString("festival_post_date"));
                festivalBoardEntity.setFestival_attachment(rs.getString("festival_attachment"));
                festivalBoardEntity.setEvent_type(rs.getInt("event_type"));
                festivalBoardEntity.setLike_that(rs.getInt("like_that"));
                festivalBoardEntity.setViews(rs.getInt("views"));
                festivalBoardEntity.setBrand_link(rs.getString("brand_link"));
                festivalBoardEntity.setBrand_sns(rs.getString("brand_sns"));
                return festivalBoardEntity;
            }
        });
        //데이터를 담은 List를 반환
        return result;
    }
    /**
     * 특정 게시물을 출력하기.
     * @param festival_no : 게시글번호(festival_no)
     * @return BoardEntity  형태의 데이터
     */
    public FestivalBoardEntity getRecordByFestivalNo(int festival_no) throws Exception {
        //generic이 BoardEntity 인 list를 선언하고  jdbc template 실행결과를 담는다.
        //특이한 점은 데이터를 하나 뽑는데도 list로 선언한다.
        //전체 출력 메소드와 매우 흡사하다
        List<FestivalBoardEntity> result = jdbcTemplate.query(
                "select * from festival where festival_no=?;", //쿼리문
                new RowMapper<FestivalBoardEntity>() {
                    @Override
                    public FestivalBoardEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                        FestivalBoardEntity festivalBoardEntity = new FestivalBoardEntity();
                        festivalBoardEntity.setFestival_no(rs.getInt("festival_no"));
                        festivalBoardEntity.setFestival_title(rs.getString("festival_title"));
                        festivalBoardEntity.setFestival_content(rs.getString("festival_content"));
                        festivalBoardEntity.setHost(rs.getString("host"));
                        festivalBoardEntity.setFestival_dist(rs.getString("festival_dist"));
                        festivalBoardEntity.setFestival_subdist(rs.getString("festival_subdist"));
                        festivalBoardEntity.setFestival_location(rs.getString("festival_location"));
                        festivalBoardEntity.setFestival_start(rs.getString("festival_start"));
                        festivalBoardEntity.setFestival_end(rs.getString("festival_end"));
                        festivalBoardEntity.setOpen_time(rs.getString("open_time"));
                        festivalBoardEntity.setFestival_post_date(rs.getString("festival_post_date"));
                        festivalBoardEntity.setFestival_attachment(rs.getString("festival_attachment"));
                        festivalBoardEntity.setEvent_type(rs.getInt("event_type"));
                        festivalBoardEntity.setLike_that(rs.getInt("like_that"));
                        festivalBoardEntity.setViews(rs.getInt("views"));
                        festivalBoardEntity.setBrand_link(rs.getString("brand_link"));
                        festivalBoardEntity.setBrand_sns(rs.getString("brand_sns"));
                        return festivalBoardEntity;
                    }
                    //쿼리끝 ? 부분에 데이터를 넣는다
                }, festival_no);
        //데이터를 담은 list를 반환 시 조건을 걸어서 조건에 맞게 보낸다
        //삼항 연산자로 isEmpty()인지 아닌지 판단해서 리턴하도록 한다!
        //empty면 null값이 반환되고, 아니면  results.get(0)이 반환된다.
        //즉, 메소드에서 정의한 리턴 형(BoardEntity )에 맞게 데이터가 리턴 될 수 있다.
        return result.isEmpty() ? null : result.get(0);

    }
    /**
     * 데이터를 insert하는 메소드
     * @param festivalBoardEntity: ExanRIO형의 데이터(DB에 삽입하고자 하는 데이터)
     * @return int형인 status를 반환한다.(아마 성공 여부인듯 하다)
     */
    public int insert(FestivalBoardEntity festivalBoardEntity) throws Exception{
        //메소드 불러올 때 로그를 남김
        logger.info("Board insert");
        //쿼리문을 적고 실행하고 리턴한다.(insert는 update)
        //sql : 데이터들을 넣음
        //insert 시  update메소드 안에 쿼리문을 적고 쿼리문에서 넣고자 하는 데이터는 ?로 처리한다 (preparedstatement 형식)
        //그리고 , 쿼리문 뒤에 ?에 해당하는 데이터를 적어준다.(template 형식)
        //BoardEntity 형의 객체에 들어있는 데이터를 get메소드로 가져온다.
        return jdbcTemplate.update(
                "insert into festival(festival_title, festival_content, host, festival_dist, festival_subdist, festival_location, festival_start, festival_end, open_time, festival_attachment, event_type, like_that, views, brand_link, brand_sns) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
                , festivalBoardEntity.getFestival_title(), festivalBoardEntity.getFestival_content(), festivalBoardEntity.getHost(), festivalBoardEntity.getFestival_dist(), festivalBoardEntity.getFestival_subdist(), festivalBoardEntity.getFestival_location(), festivalBoardEntity.getFestival_start(),
                festivalBoardEntity.getFestival_end(), festivalBoardEntity.getOpen_time(), festivalBoardEntity.getFestival_attachment(), festivalBoardEntity.getEvent_type(), festivalBoardEntity.getLike_that(), festivalBoardEntity.getViews(), festivalBoardEntity.getBrand_link(), festivalBoardEntity.getBrand_sns());
    }

    //----------------------------POPUP TABLE--------------------------------------//



}