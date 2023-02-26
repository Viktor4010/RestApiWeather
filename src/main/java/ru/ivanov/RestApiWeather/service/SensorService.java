package ru.ivanov.RestApiWeather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.RestApiWeather.models.Sensor;
import ru.ivanov.RestApiWeather.repositories.SensorRepository;

import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void register(Sensor sensor) {
        sensorRepository.save(sensor);
    }


    @Transactional(readOnly = true)
    public Optional<Sensor> findByName(String name) {
        return  sensorRepository.findSensorBySensorName(name);
    }
}
