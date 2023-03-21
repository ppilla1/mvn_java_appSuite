package io.explore.model.jpa.airport;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "PASSENGERS")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Passenger {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "AIRPORT_ID")
    private Airport airport;

    @OneToMany(mappedBy = "passenger")
    private List<Ticket> tickets = new ArrayList<>();

    public Passenger(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
