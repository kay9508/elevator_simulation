package com.assignment.elevator_simulation;

import com.assignment.elevator_simulation.entity.Elevator;
import com.assignment.elevator_simulation.enums.ElevatoerStatus;
import com.assignment.elevator_simulation.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElevatorSimulationApplication implements CommandLineRunner {

    @Autowired
    ElevatorService elevatorService;

    @Value("${elevator.count}")
    private int elevatorCount;

    public static void main(String[] args) {
        SpringApplication.run(ElevatorSimulationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < this.elevatorCount; i++) {
            Elevator elevator = new Elevator(0,0,false, ElevatoerStatus.STOP);
            elevatorService.saveElevator(elevator);
        }

        //TODO 멀티스레드 관련 임시 확인용
        elevatorService.moveElevator(1L,3);
        elevatorService.moveElevator(1L,4);
        elevatorService.moveElevator(2L,7);
    }

}
