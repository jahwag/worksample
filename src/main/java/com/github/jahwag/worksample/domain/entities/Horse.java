package com.github.jahwag.worksample.domain.entities;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Value
@Builder
@Accessors(fluent = true)
public class Horse {

    @Id
    long id;

    @NonNull
    String name;

}
