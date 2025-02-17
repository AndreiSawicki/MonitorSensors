package com.ocr.monitorsensors.data.json;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Sensor data")
public record SensorRecord(
        @Size(min = 3, message = "{validation.error.sensor.name.short}")
        @Size(max = 30, message = "{validation.error.sensor.name.long}")
        String name,

        @NotEmpty
        @Size(max = 15, message = "{validation.error.sensor.model.long}")
        String model,

        @NotNull
        RangeRecord range,

        @NotEmpty
        String type,

        @NotEmpty
        String unit,

        @Size(max = 40, message = "{validation.error.sensor.place.long}")
        String location,

        @Size(max = 200, message = "{validation.error.sensor.description.long}")
        String description) {
}
