package com.ocr.monitorsensors.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id
    @Column(name = "name")
    @Size(min = 3, message = "{validation.error.sensor.name.short}")
    @Size(max = 30, message = "{validation.error.sensor.name.long}")
    private String name;

    @Column(name = "model")
    @NotEmpty
    @Size(max = 15, message = "{validation.error.sensor.model.long}")
    private String model;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @NotNull
    private SensorType type;

    @Column(name = "range_from")
    @Positive
    private int rangeFrom;

    @Column(name = "range_to")
    @Positive
    private int rangeTo;

    @ManyToOne
    @JoinColumn(name = "unit_of_measure_id")
    @NotNull
    private UnitOfMeasure unitOfMeasure;

    @Column(name = "location")
    @Size(max = 40, message = "{validation.error.sensor.place.long}")
    private String location;

    @Column(name = "description")
    @Size(max = 200, message = "{validation.error.sensor.description.long}")
    private String description;

}
