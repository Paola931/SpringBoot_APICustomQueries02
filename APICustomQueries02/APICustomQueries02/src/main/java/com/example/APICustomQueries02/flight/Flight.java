package com.example.APICustomQueries02.flight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
enum Status{
    ONTIME,
    DELAYED,
    CANCELLED
}
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String fromAirport;
    private String toAirport;
    @Enumerated(value = EnumType.STRING)
    public Status status;
}
