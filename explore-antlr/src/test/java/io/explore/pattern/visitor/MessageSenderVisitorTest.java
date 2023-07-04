package io.explore.pattern.visitor;

import io.explore.pattern.visitor.behaviour.InsuranceMessageVisitor;
import io.explore.pattern.visitor.behaviour.Visitor;
import io.explore.pattern.visitor.model.Bank;
import io.explore.pattern.visitor.model.Company;
import io.explore.pattern.visitor.model.Resident;
import io.explore.pattern.visitor.model.Restaurant;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class MessageSenderVisitorTest {

    @Test
    public void testMessageSendBehaviourForAllClientTypes(){
        Bank bank = new Bank("SBI", "Bangalore", "121", 3);
        Company company = new Company("JP Morgan & Chase", "NY", "6", 70000);
        Resident resident = new Resident("Mr Someone", "Bangalore", "4", "Life");
        Restaurant restaurant = new Restaurant("Hard Rock", "St. Marks Road", "2", "Globally");

        Visitor messageSender = new InsuranceMessageVisitor();
        bank.accept(messageSender);
        company.accept(messageSender);
        resident.accept(messageSender);
        restaurant.accept(messageSender);

        assertTrue(true);
    }
}
