package com.ocr.monitorsensors.data.repository;

import com.ocr.monitorsensors.domain.SensorType;
import org.springframework.data.repository.CrudRepository;

public interface SensorTypeRepository extends CrudRepository<SensorType, String> {
}
