package io.explore.pattern.visitor.model;

import io.explore.pattern.visitor.behaviour.Visitor;
import lombok.Getter;

@Getter
public class Resident extends Client{

    private final String insuranceClass;

    public Resident(String name, String address, String number, String insuranceClass) {
        super(name, address, number);
        this.insuranceClass = insuranceClass;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
