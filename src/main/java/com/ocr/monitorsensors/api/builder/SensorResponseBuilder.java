package com.ocr.monitorsensors.api.builder;

import com.ocr.monitorsensors.data.json.RangeRecord;
import com.ocr.monitorsensors.data.json.SensorRecord;
import com.ocr.monitorsensors.domain.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorResponseBuilder {
    public SensorRecord build(Sensor sensor) {
        SensorRecord record = new SensorRecord(
                sensor.getName(),
                sensor.getModel(),
                new RangeRecord(sensor.getRangeFrom(), sensor.getRangeTo()),
                sensor.getType().getId(),
                sensor.getUnitOfMeasure().getId(),
                sensor.getLocation(),
                sensor.getDescription()
        );
        return record;
    }

    public List<SensorRecord> build(List<Sensor> sensors) {
        return sensors.stream().map(sensor -> build(sensor)).toList();
    }
}
