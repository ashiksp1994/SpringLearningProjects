package com.reserveappartment.appartmentreserveapp.repos;

import com.reserveappartment.appartmentreserveapp.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
