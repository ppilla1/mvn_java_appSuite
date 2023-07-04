package io.explore.pattern.visitor.behaviour;

import io.explore.pattern.visitor.model.Bank;
import io.explore.pattern.visitor.model.Company;
import io.explore.pattern.visitor.model.Resident;
import io.explore.pattern.visitor.model.Restaurant;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InsuranceMessageVisitor implements Visitor{

    @Override
    public void visit(Resident resident) {
        log.info("Sending message-> subject {}", resident.getInsuranceClass());
    }

    @Override
    public void visit(Company company) {
        log.info("Sending message-> subject {}", company.getNbrOfEmployees());
    }

    @Override
    public void visit(Bank bank) {
        log.info("Sending message-> subject {}", bank.getBranchesInsured());
    }

    @Override
    public void visit(Restaurant restaurant) {
        log.info("Sending message-> subject {}", restaurant.getAvailableAbroad());
    }
}
