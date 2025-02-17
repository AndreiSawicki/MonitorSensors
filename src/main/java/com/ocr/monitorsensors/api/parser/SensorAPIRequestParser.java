package com.ocr.monitorsensors.api.parser;

import com.ocr.monitorsensors.SensorException;
import com.ocr.monitorsensors.data.json.SensorRecord;
import com.ocr.monitorsensors.data.repository.SensorTypeRepository;
import com.ocr.monitorsensors.data.repository.UnitOfMeasureRepository;
import com.ocr.monitorsensors.domain.Sensor;
import com.ocr.monitorsensors.domain.SensorType;
import com.ocr.monitorsensors.domain.UnitOfMeasure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorAPIRequestParser {

    private final SensorTypeRepository sensorTypeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public Sensor parse(SensorRecord sensorRecord) throws SensorException {

        List<String> errorMessages = new ArrayList<>();

        Optional<SensorType> sensorType = sensorTypeRepository.findById(sensorRecord.type());
        if (sensorType.isEmpty()) {
            errorMessages.add("Sensor type not found");
        }

        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findById(sensorRecord.unit());
        if (unitOfMeasure.isEmpty()) {
            errorMessages.add("Unit of measure not found");
        }

        if (!errorMessages.isEmpty()) {
            throw new SensorException(String.join("; ", errorMessages));
        }

        Sensor sensor = new Sensor();

        sensor.setName(sensorRecord.name());

        sensor.setModel(sensorRecord.model());

        sensor.setType(sensorType.get());

        sensor.setRangeFrom(sensorRecord.range().rangeFrom());

        sensor.setRangeTo(sensorRecord.range().rangeTo());

        sensor.setUnitOfMeasure(unitOfMeasure.get());

        sensor.setLocation(sensorRecord.location());

        sensor.setDescription(sensorRecord.description());

        return sensor;
    }
}
