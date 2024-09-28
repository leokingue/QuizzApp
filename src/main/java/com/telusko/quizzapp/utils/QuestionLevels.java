package com.telusko.quizzapp.utils;

import lombok.Getter;

@Getter
public enum QuestionLevels {

    EASY("EASY");

    private String level;

    QuestionLevels(String level) {
        this.level = level;
    }
}
