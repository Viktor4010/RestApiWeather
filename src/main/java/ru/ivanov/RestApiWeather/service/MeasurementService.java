package ru.ivanov.RestApiWeather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.RestApiWeather.models.Measurement;
import ru.ivanov.RestApiWeather.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional(readOnly = true)
    public List<Measurement> findAllMeasurements() {
        return measurementRepository.findAll(Sort.by(Sort.Direction.ASC));
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        enrichMeasurement(measurement);

        measurementRepository.save(measurement);
    }

    public void enrichMeasurement(Measurement measurement) {
        // мы должны сами найти сенсор из БД по имени и вставить объект из Hibernate persistence context'а
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getSensorName()).get());

        measurement.setCreated_at(LocalDateTime.now());
    }
}
