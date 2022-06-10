package com.manager.event.repository;

import com.manager.event.TestUtils;
import com.manager.event.dto.EventType;
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
public class EventTopicRepositoryTest {

    @Autowired
    private EventTopicRepository topicRepository;

    @Test
    void saveEntityWithAddressTest() {
        //given
        EventEntity eventEntity = TestUtils.getTestEventEntityOnline();
        EventTopic topic1 = new EventTopic();
        topic1.setTopic("Hibernate is better than JPA");

        EventTopic topic2 = new EventTopic();
        topic2.setTopic("JPA is better than Hibernate");

        topic1.setEvent(eventEntity);
        topic2.setEvent(eventEntity);

        //when
        topicRepository.saveAll(List.of(topic1, topic2));

        List<EventTopic> entities = topicRepository.findAll();

        //then
        Assertions.assertThat(entities.size()).isEqualTo(2);
        Assertions.assertThat(entities.get(0).getEvent()).isNotNull();
        Assertions.assertThat(entities.get(0).getEvent().getName()).isEqualTo("Test");
        Assertions.assertThat(entities.get(0).getEvent().getDescription()).isEqualTo("Description");
        Assertions.assertThat(entities.get(0).getEvent().getType()).isEqualTo(EventType.ONLINE);
    }
}
