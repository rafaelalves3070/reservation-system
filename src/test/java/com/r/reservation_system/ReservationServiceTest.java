package com.r.reservation_system;

import com.r.reservation_system.dto.CreateReservationDTO;
import com.r.reservation_system.entity.Room;
import com.r.reservation_system.entity.User;
import com.r.reservation_system.exception.BusinessException;
import com.r.reservation_system.repository.ReservationRepository;
import com.r.reservation_system.repository.RoomRepository;
import com.r.reservation_system.repository.UserRepository;
import com.r.reservation_system.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void shouldThrowExceptionWhenThereIsTimeConflict() {
        CreateReservationDTO dto = new CreateReservationDTO();
        dto.setUserId(1L);
        dto.setRoomId(1L);
        dto.setStartDateTime(LocalDateTime.of(2026, 4, 3, 14, 0));
        dto.setEndDateTime(LocalDateTime.of(2026, 4, 3, 16, 0));

        User user = new User();
        user.setName("Rafael");
        user.setEmail("rafael@email.com");

        Room room = new Room();
        room.setName("Sala 1");
        room.setCapacity(10);
        room.setLocation("Primeiro Andar");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        Mockito.when(
                reservationRepository.findByRoomIdAndStartDateTimeLessThanAndEndDateTimeGreaterThan(
                        Mockito.isNull(),
                        Mockito.eq(dto.getEndDateTime()),
                        Mockito.eq(dto.getStartDateTime())
                )
        ).thenReturn(List.of(new com.r.reservation_system.entity.Reservation()));

        assertThrows(BusinessException.class, () -> reservationService.createReservation(dto));
    }
}