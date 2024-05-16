package org.acme.movie_rental.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.movie_rental.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer>{
}
