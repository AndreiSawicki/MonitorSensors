package com.ocr.monitorsensors.api.controller;

import com.ocr.monitorsensors.SensorException;
import com.ocr.monitorsensors.api.builder.SensorResponseBuilder;
import com.ocr.monitorsensors.data.json.SensorRecord;
import com.ocr.monitorsensors.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sensor")
@Tag(name = "Sensor operations")
public class SensorAPIController extends AbstractSensorAPIController {

    private final SensorService sensorService;
    private final SensorResponseBuilder responseBuilder;

    @Operation(summary = "Get sensor by name")
    @GetMapping("/{name}")
    public ResponseEntity<SensorRecord> viewSensor(@PathVariable(value = "name") String name) throws SensorException {
        return ResponseEntity.ok().body(
                responseBuilder.build(sensorService.getSensor(name))
        );
    }

    @Operation(summary = "Create new sensor")
    @PostMapping
    public ResponseEntity addSensor(@RequestBody @Valid SensorRecord sensorRecord) throws SensorException {
        sensorService.createSensor(sensorRecord);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update sensor parameters")
    @PutMapping
    public ResponseEntity updateSensor(@RequestBody @Valid SensorRecord sensorRecord) throws SensorException {
        sensorService.updateSensor(sensorRecord);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Receive list of sensors")
    @GetMapping("/list")
    public ResponseEntity<List<SensorRecord>> list(@RequestParam(required = false) String name, @RequestParam(required = false) String model) {
        return ResponseEntity.ok().body(
                responseBuilder.build(sensorService.findSensors(name, model))
        );
    }

    @Operation(summary = "Delete sensor by name")
    @DeleteMapping("/{name}")
    public ResponseEntity deleteSensor(@PathVariable(value = "name") String name) {
        sensorService.deleteSensor(name);
        return ResponseEntity.ok().build();
    }
}
