package com.backend.survey.controller;

import com.backend.survey.dto.ResultDTO;
import com.backend.survey.response.DataResponse;
import com.backend.survey.response.MessageResponse;
import com.backend.survey.response.ResultResponse;
import com.backend.survey.services.IResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ResultController {
    private final IResultService resultService;

    public ResultController(IResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/result")
    public ResponseEntity<?> submit(@RequestBody ResultDTO resultDTO) {
        try {
            List<ResultResponse> list = new ArrayList<>();
            list.add(resultService.submit(resultDTO));

            return ResponseEntity.ok(new DataResponse(new Date(), HttpStatus.OK.value(), HttpStatus.OK.name(), list));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
        }
    }
}
