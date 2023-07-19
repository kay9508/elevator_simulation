package com.assignment.elevator_simulation.service;

import com.assignment.elevator_simulation.entity.Elevator;
import com.assignment.elevator_simulation.enums.ElevatoerStatus;
import com.assignment.elevator_simulation.repository.ElevatorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ElevatorService {

    private final ElevatorRepository elevatorRepository;

    // 엘리베이터 상태를 데이터베이스에 저장
    public Elevator saveElevator(Elevator elevator) {
        return elevatorRepository.save(elevator);
    }

    // 엘리베이터 상태 조회
    public Elevator getElevator(Long id) {
        return elevatorRepository.findById(id).orElse(null);
    }

    // 엘리베이터 이동 명령을 받아 스레드로 엘리베이터를 제어하는 메서드
    public void moveElevator(Long id, int targetFloor) {
        Elevator elevator = elevatorRepository.findById(id).orElse(null);
        if (elevator != null) {
            if (elevator.getCurrentFloor() != elevator.getTargetFloor()) {
                elevator.setTargetFloor(targetFloor);
                if (elevator.getTargetFloor() > elevator.getCurrentFloor()) {
                    elevator.setStatus(ElevatoerStatus.UP);
                } else {
                    elevator.setStatus(ElevatoerStatus.DOWN);
                }
            }

            if (elevator.getStatus().equals(ElevatoerStatus.STOP)) {
                // 엘리베이터가 움직이고 있지 않은 경우에만 스레드를 시작.
                ElevatorThread elevatorThread = new ElevatorThread(elevator);
                elevatorThread.start();
            } else {
                log.info("엘리베이터 " + elevator.getId() + "가 움직이는 중입니다.");
            }
        }
    }

    public class ElevatorThread extends Thread {
        private Elevator elevator;

        public ElevatorThread(Elevator elevator) {
            this.elevator = elevator;
        }

        @Override
        public void run() {
            // 엘리베이터 동작 로직 작성
            // 엘리베이터 상태를 업데이트하고 데이터베이스에 저장
            // 스레드가 실행되는 동안 계속해서 엘리베이터를 제어하는 로직을 구현
            log.info("엘리베이터 스레드 시작 - 엘리베이터 : " + elevator.getId());

            // 엘리베이터 이동 로직을 작성합니다.
            while (elevator.getCurrentFloor() != elevator.getTargetFloor()) {
                if (elevator.getCurrentFloor() < elevator.getTargetFloor()) {
                    // 엘리베이터 상태를 업데이트하고 저장합니다.
                    elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                    elevator.setStatus(ElevatoerStatus.UP);
                    elevatorRepository.save(elevator);
                } else {
                    // 엘리베이터 상태를 업데이트하고 저장합니다.
                    elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                    elevator.setStatus(ElevatoerStatus.DOWN);
                    elevatorRepository.save(elevator);
                }
                try {
                    // 엘리베이터가 한 층을 이동하는 시간을 시뮬레이션합니다. (1초로 가정)
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            // 엘리베이터가 도착한 경우, 다음 목표 층을 설정하고 상태를 업데이트합니다.
            elevator.setStatus(ElevatoerStatus.STOP);
            elevator.setTargetFloor(0); // 기본 목표 층을 0으로 설정하거나 다른 방식으로 설정합니다.
            elevatorRepository.save(elevator);
        }
    }
}