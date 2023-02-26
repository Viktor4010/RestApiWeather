package ru.ivanov.RestApiWeather.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ivanov.RestApiWeather.models.Measurement;
import ru.ivanov.RestApiWeather.service.SensorService;

@Component
public class MeasurementsValidator  implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasurementsValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if (measurement.getSensor() == null){
            return;
        }

        if (sensorService.findByName(measurement.getSensor().getSensorName()).isEmpty())
            errors.rejectValue("sensor","There is no register sensor with such name");
    }
}
