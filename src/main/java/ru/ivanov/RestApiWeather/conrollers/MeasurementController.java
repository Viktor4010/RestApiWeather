package ru.ivanov.RestApiWeather.conrollers;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.RestApiWeather.dto.MeasurementDTO;
import ru.ivanov.RestApiWeather.dto.MeasurementResponse;
import ru.ivanov.RestApiWeather.utils.ErrorsUtil;
import ru.ivanov.RestApiWeather.utils.MeasurementExceptionResponse;
import ru.ivanov.RestApiWeather.exceptions.MeasurementException;
import ru.ivanov.RestApiWeather.models.Measurement;
import ru.ivanov.RestApiWeather.service.MeasurementService;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MeasurementResponse> allMeasurements() {
        MeasurementResponse measurementResponse = new MeasurementResponse(measurementService.findAllMeasurements()
                .stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(measurementResponse);
    }

    @GetMapping(value = "/rainyDaysCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long rainyDays() {

        return measurementService.findAllMeasurements().stream().filter(Measurement::getRaining).count();
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                               BindingResult bindingResult) {

        Measurement measurement = convertToMeasurement(measurementDTO);


        if (bindingResult.hasErrors())
            ErrorsUtil.errorMessageForClient(bindingResult);


        measurementService.addMeasurement(measurement);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementExceptionResponse> handler(MeasurementException e) {
        MeasurementExceptionResponse response = new MeasurementExceptionResponse(e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDto) {
        return modelMapper.map(measurementDto, Measurement.class);
    }



}
