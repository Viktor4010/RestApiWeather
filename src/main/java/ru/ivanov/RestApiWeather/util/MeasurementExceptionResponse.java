package ru.ivanov.RestApiWeather.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MeasurementExceptionResponse {
    private String msg;
    private LocalDateTime time;
}
