package com.github.jahwag.worksample.application.services;

import com.github.jahwag.worksample.domain.entities.Horse;
import com.github.jahwag.worksample.domain.entities.RaceEvent;
import com.github.jahwag.worksample.infrastructure.HorseRepository;
import com.github.jahwag.worksample.infrastructure.RaceEventRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RaceEventServiceTest {

    @Autowired
    private RaceEventRepository raceEventRepository;

    @Autowired
    private HorseRepository horseRepository;

    @BeforeEach
    @AfterEach
    public void clean() {
        horseRepository.deleteAll();
        raceEventRepository.deleteAll();
    }

    @Test
    void save() {
        // Horse
        Horse horse = Horse.builder()
                           .id(1L)
                           .name("Badger")
                           .build();
        horseRepository.save(horse);

        assertEquals(1, horseRepository.count());
        assertEquals(horse, horseRepository.findAll());

        // Event
        RaceEvent event = RaceEvent.builder()
                                   .event(RaceEvent.Type.START)
                                   .horse(horse)
                                   .time(0)
                                   .build();
        raceEventRepository.save(event);

        assertEquals(1, raceEventRepository.count());
        assertEquals(event, raceEventRepository.findAll());

    }

}