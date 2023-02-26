package ru.ivanov.RestApiWeather.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "sensor")
@Getter
@Setter
public class Sensor implements Serializable {

    @Id
    @Column(name = "sensor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Sensor name should not be empty")
    @Size(min = 3,max = 30, message = "Sensor name should be between 3 and 30 characters")
    private String sensorName;

}
