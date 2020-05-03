package com.github.jahwag.worksample.domain.entities;

import com.github.jahwag.worksample.domain.responses.EventResponse;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Value
@Builder
@Accessors(fluent = true)
public class RaceEvent {

    @NonNull
    Type event;

    @DBRef
    Horse horse;

    long time;

    public static RaceEvent of(EventResponse eventResponse) {
        Horse horse = Horse.builder()
                           .id(eventResponse.horse()
                                            .id())
                           .name(eventResponse.horse()
                                              .horse())
                           .build();

        return RaceEvent.builder()
                        .event(Type.valueOf(eventResponse.event()
                                                         .toUpperCase()))
                        .horse(horse)
                        .time(eventResponse.time())
                        .build();
    }

    public enum Type {
        START,
        FINISH
    }

}
