package com.github.jahwag.worksample.infrastructure;

import com.github.jahwag.worksample.domain.entities.RaceEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RaceEventRepository extends MongoRepository<RaceEvent, Long> {

}
