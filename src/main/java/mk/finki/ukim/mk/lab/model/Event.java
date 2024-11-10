package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    protected String name;
    protected String description;
    protected double popularityScore;
    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Event(String name, String description, double popularityScore) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
    }
}
