package io.explore.pattern.visitor.model;

import io.explore.pattern.visitor.behaviour.Visitor;
import lombok.Getter;

@Getter
public class Restaurant extends Client{
    private final String availableAbroad;


    public Restaurant(String name, String address, String number, String availableAbroad) {
        super(name, address, number);
        this.availableAbroad = availableAbroad;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
