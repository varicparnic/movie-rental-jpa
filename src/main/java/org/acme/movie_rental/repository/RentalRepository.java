package org.acme.movie_rental.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.movie_rental.entity.Rental;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RentalRepository implements PanacheRepository<Rental>{
}
