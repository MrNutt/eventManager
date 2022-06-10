package com.manager.event.service;


import com.manager.event.TestUtils;
import com.manager.event.dto.EventRequest;
import com.manager.event.dto.EventResponse;
import com.manager.event.repository.EventEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class EventServiceIntegrationTest {

    public static final EventRequest EVENT_REQUEST_ONLINE = TestUtils.getTestEventRequestOnline();
    public static final EventResponse TEST_EVENT_RESPONSE_ONLINE = TestUtils.getTestEventResponseOnline();

    @Autowired
    private EventEntityRepository repository;
    @Autowired
    private EventService service;


    @Test
    void getById() {
        service.save(EVENT_REQUEST_ONLINE);

        EventResponse eventResponse = service.getById(repository.findAll().get(0).getId());
        assertEquals(TEST_EVENT_RESPONSE_ONLINE, eventResponse);
    }
}
