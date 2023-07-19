package com.assignment.elevator_simulation.repository;

import com.assignment.elevator_simulation.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}