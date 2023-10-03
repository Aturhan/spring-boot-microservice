package com.abdullahturhan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "travel")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long travel_id;

    private String fromWhere;
    private String toWhere;
    private Double price;
    private Long customer_id;
    private Long taxi_id;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private TravelHasFinished travelHasFinished;

}
