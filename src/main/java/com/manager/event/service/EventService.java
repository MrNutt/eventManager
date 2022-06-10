package com.manager.event.service;

import com.manager.event.converter.EventConverter;
import com.manager.event.dto.EventRequest;
import com.manager.event.dto.EventResponse;
import com.manager.event.repository.EventEntity;
import com.manager.event.repository.EventEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventEntityRepository repository;
    private final EventConverter converter;

    public EventResponse getById(Long id) {
        EventEntity eventEntity = repository.getById(id);
        EventResponse response = new EventResponse();
        if (eventEntity != null) {
            response = converter.entityToResponse(eventEntity);
        }
        return response;
    }

    public EventResponse save(EventRequest eventRequest) {
        EventEntity eventEntity = converter.requestToEntity(eventRequest);
        repository.save(eventEntity);
        return converter.entityToResponse(eventEntity);
    }

}
