package com.manager.event.repository;

import com.manager.event.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class EventEntityRepositoryTest {

    @Autowired
    private EventEntityRepository repository;

    @Test
    void saveEntityWithAddressTest() {
        //given
        EventAddress address = new EventAddress();
        address.setAddress("7, Akihabara, Tokyo");
        EventEntity eventEntity = TestUtils.getTestEventEntityOnline();
        eventEntity.setAddress(address);
        //when
        repository.save(eventEntity);

        List<EventEntity> entities = repository.findAll();

        //then
        Assertions.assertThat(entities.size()).isEqualTo(1);
        Assertions.assertThat(entities.get(0).getAddress().getId()).isNotNull();
        Assertions.assertThat(entities.get(0).getAddress().getAddress()).isEqualTo("7, Akihabara, Tokyo");
    }

    @Test
    void saveEntityWithSpeakersTest() {
        //given
        EventSpeaker speaker1 = new EventSpeaker();
        EventSpeaker speaker2 = new EventSpeaker();

        speaker1.setFirstName("John");
        speaker1.setLastName("Smith");

        speaker2.setFirstName("Neo");
        speaker2.setLastName("Anderson");

        EventEntity eventEntity = TestUtils.getTestEventEntityOnline();
        eventEntity.setSpeakers(List.of(speaker1, speaker2));

        //when
        repository.save(eventEntity);

        List<EventEntity> entities = repository.findAll();

        //then
        Assertions.assertThat(entities.size()).isEqualTo(1);
        Assertions.assertThat(entities.get(0).getSpeakers().size()).isEqualTo(2);
        Assertions.assertThat(entities.get(0).getSpeakers().get(0).getFirstName()).isEqualTo("John");
        Assertions.assertThat(entities.get(0).getSpeakers().get(1).getLastName()).isEqualTo("Anderson");
    }

    @Test
    void saveEntityWithCuisineTest() {
        //given
        EventEntity eventEntity = TestUtils.getTestEventEntityOnline();
        EventCuisine eventCuisine = new EventCuisine();
        eventCuisine.setDrinksProvider("OK drinks");
        eventCuisine.setFoodProvider("OK food");
        eventEntity.setEventCuisine(eventCuisine);

        //when
        repository.save(eventEntity);

        List<EventEntity> events = repository.findAll();

        //then
        Assertions.assertThat(events.size()).isEqualTo(1);
        Assertions.assertThat(events.get(0).getEventCuisine().getFoodProvider()).isEqualTo("OK food");
        Assertions.assertThat(events.get(0).getEventCuisine().getDrinksProvider()).isEqualTo("OK drinks");
    }
}
