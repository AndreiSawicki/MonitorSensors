package com.ocr.monitorsensors.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UnitOfMeasure {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "unit_name")
    private String unitName;
}
