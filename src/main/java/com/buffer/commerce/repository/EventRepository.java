package com.buffer.commerce.repository;

import com.buffer.commerce.model.Event;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "events", path = "event")
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    Event findOneByName(final String name);
}
