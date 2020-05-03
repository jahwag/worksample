package com.github.jahwag.worksample.domain.exceptions;

import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

public class RequestCancelledException extends Exception {

    public static Mono<? extends Throwable> mono(ClientResponse clientResponse) {
        return Mono.error(new RequestCancelledException());
    }
}
