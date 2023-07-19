package com.assignment.elevator_simulation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ElevatoerStatus {
    STOP("멈춤"),
    DOWN("내려가는중"),
    UP("올라가는중");

    private String name;
}
