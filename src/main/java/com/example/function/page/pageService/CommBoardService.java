package com.example.function.page.pageService;


import com.example.function.page.pageEntity.CommBoardEntity;
import com.example.function.page.pageRepository.CommBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommBoardService {

    @Autowired
    private CommBoardRepository commBoardRepository;

    //게시글 모두 조회
    public List<CommBoardEntity> getAllBoards() {
        return commBoardRepository.findAll();
    }

    //게시글 조회
    public Optional<CommBoardEntity> getBoardId(int boardNo) {
        return commBoardRepository.findById(boardNo);
    }

    //게시글 생성
    public CommBoardEntity createBoard(CommBoardEntity commBoard) {
        return commBoardRepository.save(commBoard);
    }

    //게시글 수정
    public CommBoardEntity updateBoard(int boardNo, CommBoardEntity commBoard) {
        Optional<CommBoardEntity> existingBoard = commBoardRepository.findById(boardNo);
        if (existingBoard.isPresent()) {

            //기존 엔티티 가져옴
            CommBoardEntity updatedBoard = existingBoard.get();

            //업데이트 내용 설정
            updatedBoard.setBoard_title(commBoard.getBoard_title());
            updatedBoard.setBoard_content(commBoard.getBoard_content());
            updatedBoard.setUser_name(commBoard.getUser_name());
            updatedBoard.setBoard_post_date(commBoard.getBoard_post_date());
            updatedBoard.setViews(commBoard.getViews());

            return commBoardRepository.save(updatedBoard);
        } else { //엔티티가 존재하지 않을 경우
            return null;
        }
    }

    //게시글 삭제
    public void deleteBoard(int boardNo) {
        commBoardRepository.deleteById(boardNo);
    }
}
