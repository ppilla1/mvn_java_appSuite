package io.explore.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Foo {
    private long id;
    public Foo(long id) {
        this.id = id;
    }
}
