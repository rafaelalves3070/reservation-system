package com.r.reservation_system.repository;

import com.r.reservation_system.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoomIdAndStartDateTimeLessThanAndEndDateTimeGreaterThan(
            Long roomId,
            LocalDateTime endDateTime,
            LocalDateTime startDateTime
    );
}