package com.ocr.monitorsensors;

import com.ocr.monitorsensors.data.repository.SensorRepository;
import com.ocr.monitorsensors.data.repository.SensorTypeRepository;
import com.ocr.monitorsensors.data.repository.UnitOfMeasureRepository;
import com.ocr.monitorsensors.domain.Sensor;
import com.ocr.monitorsensors.domain.SensorType;
import com.ocr.monitorsensors.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test"})
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc(addFilters = false)
public class MonitorSensorsAPITests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    SensorRepository sensorRepository;

    @MockitoBean
    SensorTypeRepository sensorTypeRepository;

    @MockitoBean
    UnitOfMeasureRepository unitOfMeasureRepository;

    private final String API_URL = "/api/v1/sensor";

    @BeforeEach
    public void init() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure("bar", "bar");
        SensorType sensorType = new SensorType("pressure", "pressure", null);

        List<Sensor> sensorList = new ArrayList<>();

        Sensor sensor1 = Sensor.builder()
                .name("Sensor1")
                .model("model-1")
                .rangeFrom(1)
                .rangeTo(22)
                .type(sensorType)
                .unitOfMeasure(unitOfMeasure)
                .location("room1")
                .description("Sensor at room 1")
                .build();

        sensorList.add(sensor1);

        doAnswer(args -> {
            String name = args.getArgument(0);
            return sensorList.stream().filter(it -> it.getName().equals(name)).findFirst();
        }).when(sensorRepository).findById(anyString());

        doAnswer(args -> {
            Sensor sensor = args.getArgument(0);
            sensorList.add(sensor);
            return sensor;
        }).when(sensorRepository).save(any());

        doAnswer(args -> Optional.of(sensorType)).when(sensorTypeRepository).findById(any());

        doAnswer(args -> Optional.of(unitOfMeasure)).when(unitOfMeasureRepository).findById(any());

    }

    @Test
    @DisplayName(
            """
                    get sensor: ok
                     """
    )
    public void getSensor_OK() throws Exception {
        testGetResponse("/Sensor1", "getSensor-Ok");
    }

    @Test
    @DisplayName(
            """
                    get sensor: not found
                     """
    )
    public void getSensor_Error() throws Exception {
        testGetResponse("/Sensor-Error", "getSensor-Error");
    }

    @Test
    @DisplayName(
            """
                    create sensor: ok
                     """
    )
    public void createSensor_Ok() throws Exception {
        mockMvc.perform(post(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getFromFile("createSensor-Ok-request.json")))
                .andExpect(status().isOk());
        Sensor sensor = sensorRepository.findById("SensorTest").get();
        assertThat(sensor).
                isNotNull().
                hasFieldOrPropertyWithValue("name", "SensorTest").
                hasFieldOrPropertyWithValue("model", "test").
                hasFieldOrPropertyWithValue("rangeFrom", 22).
                hasFieldOrPropertyWithValue("rangeTo", 45).
                hasFieldOrPropertyWithValue("location", "kitchen").
                hasFieldOrPropertyWithValue("description", "Sensor description");

        assertThat(sensor.getType()).
                isNotNull().
                hasFieldOrPropertyWithValue("id", "pressure");

        assertThat(sensor.getUnitOfMeasure()).
                isNotNull().
                hasFieldOrPropertyWithValue("id", "bar");
    }

    private static String getFromFile(String fileLocation) throws IOException {
        File file = new File("src/test/resources/api/v1/sensor/" + fileLocation);
        return new String(Files.readAllBytes(file.toPath()));
    }

    private void testGetResponse(String requestParam, String fileName) throws Exception {
        mockMvc.perform(get(API_URL + requestParam))
                .andExpect(status().isOk()).andExpect(content().json(
                        getFromFile(fileName + "-response.json")
                ));
    }
}
