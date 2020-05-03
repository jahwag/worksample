package com.github.jahwag.worksample.application.services;

import com.github.jahwag.worksample.domain.entities.RaceEvent;
import com.github.jahwag.worksample.domain.exceptions.RequestCancelledException;
import com.github.jahwag.worksample.domain.responses.EventResponse;
import com.github.jahwag.worksample.infrastructure.HorseRepository;
import com.github.jahwag.worksample.infrastructure.RaceEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@ConditionalOnProperty("worksample.api.url")
public class RaceEventService {

    private final HorseRepository horseRepository;

    private final RaceEventRepository raceEventRepository;

    private final String apiUrl;

    public RaceEventService(HorseRepository horseRepository,
                            RaceEventRepository raceEventRepository,
                            @Value("${worksample.api.url}") String apiUrl) {
        this.horseRepository = horseRepository;
        this.raceEventRepository = raceEventRepository;
        this.apiUrl = apiUrl;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        Thread.currentThread()
              .setName(getClass().getSimpleName() + "Thread");
        log.info("Connecting to API ({})", apiUrl);

        WebClient.create(apiUrl)
                 .get()
                 .retrieve()
                 .onStatus(HttpStatus.NO_CONTENT::equals, RequestCancelledException::mono)
                 .bodyToMono(EventResponse.class)
                 .repeat()
                 .retry(RequestCancelledException.class::isInstance)
                 .map(RaceEvent::of)
                 .subscribe(this::save);
    }


    void save(RaceEvent event) {
        if (!horseRepository.existsById(event.horse()
                                             .id())) {
            horseRepository.save(event.horse());
        }
        raceEventRepository.save(event);
        log.info("Saved {}", event);
    }

}
