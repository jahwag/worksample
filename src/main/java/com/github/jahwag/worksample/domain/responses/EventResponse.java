package com.github.jahwag.worksample.domain.responses;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Builder
@Accessors(fluent = true)
public class EventResponse {

    String event;

    Horse horse;

    long time;

    @Value
    @Accessors(fluent = true)
    public static class Horse {

        long id;

        String horse;

    }

}
