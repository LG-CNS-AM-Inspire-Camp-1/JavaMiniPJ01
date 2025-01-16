package org.example.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Movie {
    private String id; // 영화 고유 ID
    private String title; // 제목
    private String director; // 감독
    private int year; // 제작 연도
}