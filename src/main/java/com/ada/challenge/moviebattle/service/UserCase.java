package com.ada.challenge.moviebattle.service;

public interface UserCase<Request, Response> {
    Response execute(Request request);
}
