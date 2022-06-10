package com.manager.event.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class EventCuisine {

    private String drinksProvider;
    private String foodProvider;
}
