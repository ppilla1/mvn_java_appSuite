package io.explore.pattern.visitor.behaviour;

import io.explore.pattern.visitor.model.Bank;
import io.explore.pattern.visitor.model.Company;
import io.explore.pattern.visitor.model.Resident;
import io.explore.pattern.visitor.model.Restaurant;

public interface Visitor {
    void visit(Resident resident);
    void visit(Company company);
    void visit(Bank bank);
    void visit(Restaurant restaurant);
}
