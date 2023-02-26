package ru.ivanov.RestApiWeather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MeasurementResponse {
    private List<MeasurementDTO> responseToClient;

}
