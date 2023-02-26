package ru.ivanov.RestApiWeather.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.ivanov.RestApiWeather.models.Sensor;

@Getter
@Setter
public class MeasurementDTO {
    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull
    private Sensor sensor;
}
