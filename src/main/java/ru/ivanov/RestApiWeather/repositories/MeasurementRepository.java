package ru.ivanov.RestApiWeather.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.RestApiWeather.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}
