package com.assignment.elevator_simulation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long id;

    private String name;

    @Column(name = "current_floor")
    private int currentFloor;

    @Column(name = "target_floor")
    private int targetFloor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elevator_id")
    private Elevator elevator;

}
