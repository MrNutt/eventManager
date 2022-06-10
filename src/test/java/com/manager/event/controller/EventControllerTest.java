package com.manager.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manager.event.TestUtils;
import com.manager.event.converter.EventConverter;
import com.manager.event.dto.EventRequest;
import com.manager.event.dto.EventResponse;
import com.manager.event.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ActiveProfiles("test")
class EventControllerTest {

    public static final EventResponse EVENT_RESPONSE_ONLINE = TestUtils.getTestEventResponseOnline();
    public static final EventRequest EVENT_REQUEST_OFFLINE = TestUtils.getTestEventRequestOffline();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService service;

    @MockBean
    private EventConverter converter;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        when(service.getById(anyLong())).thenReturn(EVENT_RESPONSE_ONLINE);
    }

    @Test
    void getEventById() throws Exception {
        mockMvc.perform(get("/api/v1/event/1"))
               .andDo(print()).andExpect(status().isOk())
               .andExpect(content().string(containsString(objectMapper.writeValueAsString(EVENT_RESPONSE_ONLINE))))
               .andReturn();
    }

    @Test
    void saveEvent() throws Exception {
        mockMvc.perform(post("/api/v1/event")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(EVENT_REQUEST_OFFLINE)))
               .andExpect(status().isOk());
    }
}
