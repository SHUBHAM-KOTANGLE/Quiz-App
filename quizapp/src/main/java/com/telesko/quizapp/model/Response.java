package com.telesko.quizapp.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Response {

    private int id;
    private String response;
}
