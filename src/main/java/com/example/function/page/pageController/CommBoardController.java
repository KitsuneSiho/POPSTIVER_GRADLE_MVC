package com.example.function.page.pageController;


import com.example.function.page.pageEntity.CommBoardEntity;
import com.example.function.page.pageService.CommBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/boards")
public class CommBoardController {

    @Autowired
    private CommBoardService commBoardService;

    //모든 게시글 목록
    @GetMapping
    public String getAllBoards(Model model) {
    List<CommBoardEntity> boards = commBoardService.getAllBoards();
    model.addAttribute("boards", boards);
        return "list";
    }

    //게시글 상세 조회
    @GetMapping("/{boardNo}")
    public ResponseEntity<CommBoardEntity> getBoard(@PathVariable("boardNo") int boardNo) {
        Optional<CommBoardEntity> commBoard = commBoardService.getBoardId(boardNo);
        //******************************람다식 공부하기******************************
        return commBoard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
        /*람다식 대체할 경우
        Optional<CommBoardEntity> commBoard = commBoardService.getBoardId(boardNo);
        if (commBoard.isPresent()) {
            return ResponseEntity.ok(commBoard.get());
        } else {
            return ResponseEntity.notFound().build();
        }*/


    //게시글 생성
    @PostMapping
    public ResponseEntity<CommBoardEntity> createBoard(@RequestBody CommBoardEntity commBoard) {
        CommBoardEntity createdBoard = commBoardService.createBoard(commBoard);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard); //응답 본문에 createdBoard 객체 담아 반환
        //HTTP상태코드가 201 CREATED         -> 생성작업 성공시 반환되는 HTTP코드
    }

    //게시글 수정
    @PutMapping("/{boardNo}")
    public ResponseEntity<CommBoardEntity> updateBoard(@PathVariable("boardNo") int boardNo,
                                                       @RequestBody CommBoardEntity commBoard) {
        CommBoardEntity updatedBoard = commBoardService.updateBoard(boardNo, commBoard);
        return Optional.ofNullable(updatedBoard) //updatedBoard가 Null일 수 있음
                       .map(board -> ResponseEntity.ok(board)) // 값이 있을 경우, 200OK코드와 함께 반환
                       .orElseGet(() -> ResponseEntity.notFound().build()); //Null일 경우
    }

    //게시글 삭제
    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardNo") int boardNo) { //응답 본문 없음
        commBoardService.deleteBoard(boardNo);
        return ResponseEntity.noContent().build();
    }
}
