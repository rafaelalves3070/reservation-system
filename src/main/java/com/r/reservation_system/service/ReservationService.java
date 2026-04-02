package com.r.reservation_system.service;

import com.r.reservation_system.dto.CreateReservationDTO;
import com.r.reservation_system.entity.Reservation;
import com.r.reservation_system.entity.Room;
import com.r.reservation_system.entity.User;
import com.r.reservation_system.enums.ReservationStatus;
import com.r.reservation_system.exception.BusinessException;
import com.r.reservation_system.repository.ReservationRepository;
import com.r.reservation_system.repository.RoomRepository;
import com.r.reservation_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            UserRepository userRepository,
            RoomRepository roomRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    public Reservation createReservation(CreateReservationDTO dto) {
        if (dto.getStartDateTime().isAfter(dto.getEndDateTime())
                || dto.getStartDateTime().isEqual(dto.getEndDateTime())) {
            throw new BusinessException("Start date/time must be before end date/time.");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new BusinessException("User not found."));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new BusinessException("Room not found."));

        List<Reservation> conflicts =
                reservationRepository.findByRoomIdAndStartDateTimeLessThanAndEndDateTimeGreaterThan(
                        room.getId(),
                        dto.getEndDateTime(),
                        dto.getStartDateTime()
                );

        if (!conflicts.isEmpty()) {
            throw new BusinessException("There is already a reservation for this room in the selected time range.");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setStartDateTime(dto.getStartDateTime());
        reservation.setEndDateTime(dto.getEndDateTime());
        reservation.setStatus(ReservationStatus.PENDING);

        return reservationRepository.save(reservation);
    }
}