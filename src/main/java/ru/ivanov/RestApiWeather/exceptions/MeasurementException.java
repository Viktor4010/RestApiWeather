package ru.ivanov.RestApiWeather.exceptions;

public class MeasurementException extends RuntimeException {
    public MeasurementException(String msg) {
        super(msg);
    }
}
