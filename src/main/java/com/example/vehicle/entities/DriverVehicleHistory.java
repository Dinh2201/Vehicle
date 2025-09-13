package com.example.vehicle.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "driver_vehicle_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverVehicleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_seq_gen")
    @SequenceGenerator(
            name = "history_seq_gen",
            sequenceName = "driver_vehicle_history_history_id_seq",
            allocationSize = 1
    )
     Long historyId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    Driver driver;

    @Column(name = "start_date")
    LocalDateTime startDate;

    @Column(name = "end_date")
    LocalDateTime endDate;


}
