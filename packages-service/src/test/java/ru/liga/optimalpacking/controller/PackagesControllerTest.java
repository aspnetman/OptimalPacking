package ru.liga.optimalpacking.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.optimalpacking.App;
import ru.liga.optimalpacking.container.AbstractPostgresContainer;
import ru.liga.optimalpacking.infrastructure.schedule.ProcessOutboxMessagesJob;
import ru.liga.optimalpacking.shared.OutboxMessageRepository;

@SpringBootTest(classes = App.class, properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
@Transactional
public class PackagesControllerTest extends AbstractPostgresContainer {

    @Autowired
    private MockMvc mockMvc;

    @MockitoSpyBean
    OutboxMessageRepository outboxRepository;

    @MockitoBean
    ProcessOutboxMessagesJob processOutboxMessagesJob;

    @Test
    void testDeleteParcel_success() throws Exception {
        String expectedResponseJson = """
{
  "result": {
    "status": 200,
    "code": "WBEX.00000",
    "message": "OK"
  },
  "data": {}
}
""";

        mockMvc.perform(delete("/api/v1/packages/Parcel_1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseJson));
    }

    @Test
    void testEditParcel_success() throws Exception {
        String expectedResponseJson = """
{
  "result": {
    "status": 200,
    "code": "WBEX.00000",
    "message": "OK"
  },
  "data": {}
}
""";

        mockMvc.perform(put("/api/v1/packages/Parcel_1")
                        .param("form", "rectangular")
                        .param("symbol", "R")
                        .param("width", "10")
                        .param("height", "20")
                        .param("newName", "new-name"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseJson));
    }

    @Test
    void testGetParcel_success() throws Exception {
        String expectedResponseJson = """
{
  "result": {
    "status": 200,
    "code": "WBEX.00000",
    "message": "OK"
  },
  "data": {
    "parcel": {
      "name": "Parcel_1",
      "form": "Rectangular",
      "symbol": "R",
      "width": 50,
      "height": 30
    }
  }
}""";

        mockMvc.perform(get("/api/v1/packages/Parcel_1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseJson));
    }

    @Test
    void testGetParcels_success() throws Exception {
        String expectedResponseJson = """

                {
  "result": {
    "status": 200,
    "code": "WBEX.00000",
    "message": "OK"
  },
  "data": {
    "parcels": [
      {
        "name": "L-образная фигура",
        "form": "\\"L-образная фигура\\"",
        "symbol": "#",
        "width": 3,
        "height": 3
      },
      {
        "name": "Parcel_1",
        "form": "Rectangular",
        "symbol": "R",
        "width": 50,
        "height": 30
      },
      {
        "name": "P-образная фигура",
        "form": "\\"P-образная фигура\\"",
        "symbol": "#",
        "width": 3,
        "height": 3
      },
      {
        "name": "Квадрат",
        "form": "\\"Квадрат\\"",
        "symbol": "#",
        "width": 2,
        "height": 2
      },
      {
        "name": "Куб",
        "form": "\\"Куб\\"",
        "symbol": "#",
        "width": 3,
        "height": 3
      },
      {
        "name": "Треугольник",
        "form": "\\"Треугольник\\"",
        "symbol": "#",
        "width": 3,
        "height": 3
      },
      {
        "name": "Штанга",
        "form": "\\"Штанга\\"",
        "symbol": "#",
        "width": 3,
        "height": 3
      }
    ]
  }
}""";

        mockMvc.perform(get("/api/v1/packages")
                        .param("offset", "0")
                        .param("limit", "10"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expectedResponseJson));
    }
}