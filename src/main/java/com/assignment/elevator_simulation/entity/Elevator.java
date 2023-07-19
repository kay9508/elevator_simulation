package com.assignment.elevator_simulation.entity;

import com.assignment.elevator_simulation.enums.ElevatoerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.type.YesNoConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "elevator")
public class Elevator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "elevator_id")
    private Long id;

    @Column(name = "current_floor")
    private int currentFloor;

    @Column(name = "target_floor")
    private int targetFloor;

    @Column(name = "is_running")
    @Convert(converter = YesNoConverter.class)
    private boolean isRunning;

    @Enumerated(EnumType.STRING)
    private ElevatoerStatus status;

    @OneToMany(mappedBy = "elevator", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Passenger> passengers = new ArrayList<>();

    public Elevator(int currentFloor, int targetFloor, boolean isRunning, ElevatoerStatus status) {
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
        this.isRunning = isRunning;
        this.status = status;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    public void setStatus(ElevatoerStatus status) {
        this.status = status;
    }

    public void setTargetFloor(int floor) {
        this.targetFloor = floor;
    }
}
