package io.explore.model.jpa.airport;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "AIRPORTS")
@Access(AccessType.FIELD)
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Airport {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "airport")
    private List<Passenger> passengers = new ArrayList<>();

    public Airport(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }
}
