package ru.ivanov.RestApiWeather.conrollers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.RestApiWeather.dto.SensorDto;
import ru.ivanov.RestApiWeather.exceptions.MeasurementException;
import ru.ivanov.RestApiWeather.models.Sensor;
import ru.ivanov.RestApiWeather.service.SensorService;
import ru.ivanov.RestApiWeather.util.ErrorsUtil;
import ru.ivanov.RestApiWeather.util.MeasurementExceptionResponse;
import ru.ivanov.RestApiWeather.util.SensorValidator;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "sensors")
public class SensorController {

    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;
    private final SensorService sensorService;

    @Autowired
    public SensorController(ModelMapper modelMapper, SensorValidator sensorValidator, SensorService sensorService) {
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
        this.sensorService = sensorService;
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> registerNewSensor(@RequestBody @Valid SensorDto sensorDto,
                                                        BindingResult bindingResult) {
        Sensor sensor = convertToSensor(sensorDto);

        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors())
            ErrorsUtil.errorMessageForClient(bindingResult);


        sensorService.register(sensor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Sensor convertToSensor(SensorDto sensorDto) {
        return modelMapper.map(sensorDto, Sensor.class);
    }


    @ExceptionHandler
    private ResponseEntity<MeasurementExceptionResponse> handler(MeasurementException e) {
        MeasurementExceptionResponse response = new MeasurementExceptionResponse(e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
