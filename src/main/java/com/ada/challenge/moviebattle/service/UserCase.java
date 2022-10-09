package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.service.exceptions.BusinessException;

public interface UserCase<Request, Response> {
    Response execute(Request request) throws BusinessException;
}
