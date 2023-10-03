package com.abdullahturhan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "taxi")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Taxi {

    @Id
    @SequenceGenerator(name = "taxi_id_sequence",
            sequenceName = "taxi_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "taxi_id_sequence")
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName",column = @Column(name = "driver_firstName")),
            @AttributeOverride(name = "lastName",column = @Column(name = "driver_lastName"))
    })
    private Driver driver;
    @Enumerated(EnumType.STRING)
    private TaxiStatus taxiStatus;
    private Double tripPoint;



}
