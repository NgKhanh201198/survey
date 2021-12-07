package com.backend.survey.controller;

import com.backend.survey.repository.IAnswerRepository;
import com.backend.survey.response.MessageResponse;
import com.backend.survey.services.IAnswerService;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class AnswerController {
    private final IAnswerRepository answerRepository;
    private final IAnswerService answerService;

    public AnswerController(IAnswerRepository answerRepository, IAnswerService answerService) {
        this.answerRepository = answerRepository;
        this.answerService = answerService;
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity<?> addUser(@PathVariable("id") long id) {
        try {
            if (answerRepository.existsByAnswerId(id)) {
                answerService.deleteAnswer(id);
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), "Không tìm thấy id = " + id));
            }
            return ResponseEntity.ok(new MessageResponse(new Date(), HttpStatus.OK.value(), HttpStatus.OK.name(), "Xóa thành công!"));
        } catch (DataException exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
        }
    }
}
