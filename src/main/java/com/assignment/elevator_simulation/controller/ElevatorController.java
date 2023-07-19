package com.assignment.elevator_simulation.controller;

import com.assignment.elevator_simulation.service.ElevatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ElevatorController {

    private final ElevatorService elevatorService;


}
