package com.backend.survey.services;

import com.backend.survey.dto.ResultDTO;
import com.backend.survey.response.ResultResponse;

public interface IResultService {
    ResultResponse submit(ResultDTO resultDTO);
}
