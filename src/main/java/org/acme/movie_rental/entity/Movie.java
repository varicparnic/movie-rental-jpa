package org.acme.movie_rental.entity;

import jakarta.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title")
    public String title;
    @Column(name = "director")
    public String director;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    public Genre genre;
}
