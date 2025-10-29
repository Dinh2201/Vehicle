package com.example.vehicle.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_history_seq_gen")
    @SequenceGenerator(
            name = "booking_history_seq_gen",
            sequenceName = "booking_history_id_seq",
            allocationSize = 1
    )
    Long id; //unique, composite unit

    @Column(name = "booking_id")
    Long bookingId;


    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    Driver driver;

    @Column(name = "booking_status")
    String bookingStatus;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
