package com.manager.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventEntityRepository extends JpaRepository<EventEntity, Long> {
}
