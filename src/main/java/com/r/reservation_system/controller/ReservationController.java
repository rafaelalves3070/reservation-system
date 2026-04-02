package com.r.reservation_system.controller;

import com.r.reservation_system.dto.CreateReservationDTO;
import com.r.reservation_system.entity.Reservation;
import com.r.reservation_system.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestBody CreateReservationDTO dto) {
        return reservationService.createReservation(dto);
    }
}