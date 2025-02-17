package com.ocr.monitorsensors.data.repository;

import com.ocr.monitorsensors.domain.Sensor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorRepository extends CrudRepository<Sensor, String> {

    @Modifying
    @Query("""
            update Sensor s set s.model = :#{#sensor.model},
                        s.rangeFrom = :#{#sensor.rangeFrom},
                        s.rangeTo = :#{#sensor.rangeTo},
                        s.type = :#{#sensor.type},
                        s.unitOfMeasure = :#{#sensor.unitOfMeasure},
                        s.location = :#{#sensor.location},
                        s.description = :#{#sensor.description}"""
    )
    void update(@Param("sensor") Sensor sensor);

    @Query("""
            select s from Sensor s
            where (:name is null or s.name like %:name%)
                and (:model is null or s.model like %:model%)"""
    )
    List<Sensor> findByNameAndModel(String name, String model);
}
