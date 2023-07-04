package io.explore.pattern.visitor.model;

import io.explore.pattern.visitor.behaviour.Visit;
import lombok.Data;

@Data
public abstract class Client implements Visit {
    private final String name;
    private final String address;
    private final String number;
}
