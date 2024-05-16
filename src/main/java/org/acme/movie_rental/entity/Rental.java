package org.acme.movie_rental.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    public Movie movie;

    @Column(name = "rental_date")
    public LocalDate rentalDate;
    @Column(name = "return_date")
    public LocalDate returnDate;
}
