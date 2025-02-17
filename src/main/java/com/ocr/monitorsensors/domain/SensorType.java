package com.ocr.monitorsensors.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SensorType {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "type_name")
    private String typeName;

    @OneToMany
    @JoinColumn(name = "type_id")
    private Collection<Sensor> sensorList;
}
