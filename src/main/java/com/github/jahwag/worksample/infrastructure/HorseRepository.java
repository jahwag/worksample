package com.github.jahwag.worksample.infrastructure;

import com.github.jahwag.worksample.domain.entities.Horse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HorseRepository extends MongoRepository<Horse, Long> {

}
