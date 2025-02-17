package com.ocr.monitorsensors.api.controller;

import com.ocr.monitorsensors.SensorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public abstract class AbstractSensorAPIController {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    private void handleException(Exception exception) throws SensorException {
        log.error("An error occurred on request handling: {}", exception.getMessage(), exception);

        throw new SensorException("An error occurred on request handling");
    }

    //TODO: Exception Response
    @ResponseBody
    @ExceptionHandler(SensorException.class)
    private String handleException(SensorException exception) {
        return "{\"error\":\"" + exception.getMessage() + "\"}";
    }


}
