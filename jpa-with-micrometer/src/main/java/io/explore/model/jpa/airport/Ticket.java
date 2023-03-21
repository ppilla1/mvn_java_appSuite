package io.explore.model.jpa.airport;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TICKETS")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Ticket {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "NUMBER")
    private String number;

    @ManyToOne
    @JoinColumn(name = "PASSENGER_ID")
    private Passenger passenger;

    public Ticket(long id, String number) {
        this.id = id;
        this.number = number;
    }
}
