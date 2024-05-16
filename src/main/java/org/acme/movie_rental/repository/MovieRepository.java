package org.acme.movie_rental.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.movie_rental.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {
    // Zde můžete přidat další metody specifické pro Movie, pokud potřebujete
}
