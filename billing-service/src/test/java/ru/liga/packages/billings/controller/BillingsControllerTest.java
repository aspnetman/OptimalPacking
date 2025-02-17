package ru.liga.packages.billings.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.liga.optimalpacking.App;
import ru.liga.packages.billings.container.AbstractPostgresContainer;

import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = App.class, properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class BillingsControllerTest extends AbstractPostgresContainer {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBillingDetail_withValidRequest_shouldReturnValidResponse() throws Exception {
        String expectedResponseJson = """
                  {
                    "result": {
                    "status": 200,
                    "code": "WBEX.00000",
                    "message": "OK"
                    },
                    "data": {
                      "billings": [
                      {
                        "userId": "8",
                        "description": null,
                        "type": "погрузка",
                        "date": "2025-02-10T00:00:00Z",
                        "quantity": 100,
                        "cost": 500.00
                      }
                    ]
                  }
                }
                """;

        mockMvc.perform(get("/api/v1/billings")
                        .queryParam("userId", "8")
                        .queryParam("from", OffsetDateTime.parse("2025-02-09T12:06:11.808382+00:00").toString())
                        .queryParam("to", OffsetDateTime.parse("2025-02-11T12:06:09.724165+00:00").toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseJson));
    }
}