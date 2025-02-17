package com.ocr.monitorsensors.service;

import com.ocr.monitorsensors.SensorException;
import com.ocr.monitorsensors.api.parser.SensorAPIRequestParser;
import com.ocr.monitorsensors.data.json.SensorRecord;
import com.ocr.monitorsensors.data.repository.SensorRepository;
import com.ocr.monitorsensors.domain.Sensor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorAPIRequestParser requestParser;

    public Sensor getSensor(String name) throws SensorException {
        return sensorRepository.findById(name).orElseThrow(() -> new SensorException("Sensor not found"));
    }

    @Transactional
    public void createSensor(SensorRecord sensorRecord) throws SensorException {
        if (!sensorRepository.existsById(sensorRecord.name())) {
            Sensor sensor = requestParser.parse(sensorRecord);
            sensorRepository.save(sensor);
            log.info("Sensor created: {}", sensor);
        } else {
            log.info("Sensor already exists: {}", sensorRecord.name());
            throw new SensorException("Sensor already exists");
        }
    }

    @Transactional
    public void updateSensor(SensorRecord sensorRecord) throws SensorException {
        if (sensorRepository.existsById(sensorRecord.name())) {
            Sensor sensor = requestParser.parse(sensorRecord);
            sensorRepository.update(sensor);
            log.info("Sensor updated: {}", sensor.getName());
        } else {
            log.info("No such sensor: {}", sensorRecord.name());
            throw new SensorException("No such sensor");
        }
    }

    public void deleteSensor(String name) {
        sensorRepository.deleteById(name);
        log.info("Deleted Sensor: {}", name);
    }

    public List<Sensor> findSensors(String name, String model) {
        return sensorRepository.findByNameAndModel(name, model);
    }
}
