package ru.ivanov.RestApiWeather.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@Getter
@Setter
public class Measurement {

    @Id
    @Column(name = "measurement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;


    @Column(name = "raining")
    @NotNull
    private Boolean raining;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime created_at;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;
}
