package com.manager.event.converter;

import com.manager.event.TestUtils;
import com.manager.event.dto.EventRequest;
import com.manager.event.dto.EventResponse;
import com.manager.event.repository.EventEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class EventConverterTest {

    public static final EventEntity EVENT_ENTITY_ONLINE = TestUtils.getTestEventEntityOnline();
    public static final EventEntity EVENT_ENTITY_OFFLINE = TestUtils.getTestEventEntityOffline();
    public static final EventResponse EVENT_RESPONSE_ONLINE = TestUtils.getTestEventResponseOnline();
    public static final EventRequest EVENT_REQUEST_OFFLINE = TestUtils.getTestEventRequestOffline();

    private EventConverter eventConverter;

    @BeforeEach
    public void setUp() {
        eventConverter = new EventConverter();
    }

    @Test
    public void whenConvertEntityToResponse() {
        EventResponse actualResponse = eventConverter.entityToResponse(EVENT_ENTITY_ONLINE);
        assertEquals(EVENT_RESPONSE_ONLINE, actualResponse);
        System.out.println();
    }

    @Test
    public void whenConvertRequestToEntity() {
        EventEntity actualEntity = eventConverter.requestToEntity(EVENT_REQUEST_OFFLINE);
        assertEquals(EVENT_ENTITY_OFFLINE, actualEntity);
    }
}