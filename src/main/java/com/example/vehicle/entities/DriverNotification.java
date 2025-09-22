package com.example.vehicle.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "driver_notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    @JsonIgnore
    Driver driver;

     String message;

     LocalDateTime createdAt = LocalDateTime.now();

    public DriverNotification(Driver driver, String message, LocalDateTime createdAt) {
        this.driver = driver;
        this.message = message;
        this.createdAt = createdAt;
    }

}


