package com.backend.survey.controller;

import com.backend.survey.dto.QuestionDTO;
import com.backend.survey.response.DataResponse;
import com.backend.survey.response.MessageResponse;
import com.backend.survey.services.IQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {
    private final IQuestionService questionService;

    public QuestionController(IQuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<?> getByQuestionId(@PathVariable("id") long id) {
        try {
            List<QuestionDTO> list = new ArrayList<>();
            list.add(questionService.getByQuestionId(id));
            return ResponseEntity.ok(new DataResponse(new Date(), HttpStatus.OK.value(), HttpStatus.OK.name(), list));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
        }
    }

    @GetMapping("/question")
    public ResponseEntity<?> getAllQuestion() {
        try {
            List<QuestionDTO> list = questionService.getAllQuestion();

            return ResponseEntity.ok(new DataResponse(new Date(), HttpStatus.OK.value(), HttpStatus.OK.name(), list));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
        }
    }

    @PostMapping("/question")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDTO questionDTO) {
        try {
            questionService.addQuestion(questionDTO);

            return ResponseEntity.ok(new MessageResponse(new Date(), HttpStatus.OK.value(), HttpStatus.OK.name(), "Thêm câu hỏi thành công!"));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
        }
    }

    @PutMapping("/question/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") long id, @RequestBody QuestionDTO questionDTO) {
        try {
            if (questionService.existsByQuestionId(id)) {
                questionService.updateQuestion(id, questionDTO);
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.name(), "Không tìm thấy id = " + id));
            }
            return ResponseEntity.ok(new MessageResponse(new Date(), HttpStatus.OK.value(), HttpStatus.OK.name(), "Cập nhật câu hỏi thành công!"));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
        }
    }
}
