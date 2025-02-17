package com.ocr.monitorsensors.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Positive;

@Schema(description = "Range")
public record RangeRecord(
        @Positive
        @JsonProperty("from")
        int rangeFrom,

        @Positive
        @JsonProperty("to")
        int rangeTo) {
    @AssertTrue(message = "Invalid range")
    private boolean isValid() {
        return rangeFrom > rangeTo;
    }
}
