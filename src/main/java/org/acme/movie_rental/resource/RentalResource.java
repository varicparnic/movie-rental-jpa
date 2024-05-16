package org.acme.movie_rental.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import org.acme.movie_rental.repository.RentalRepository;
import org.acme.movie_rental.entity.Rental;

@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RentalResource {

    @Inject
    RentalRepository rentalRepository;

    @GET
    public Response getAllRentals() {
        return Response.ok(rentalRepository.listAll()).build();
    }

    @POST
    public Response createRental(Rental rental) {
        rentalRepository.persist(rental);
        return Response.status(Response.Status.CREATED).entity(rental).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRental(@PathParam("id") Long id, Rental updatedRental) {
        Rental rental = rentalRepository.findById(id);
        if (rental == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        rental.customer = updatedRental.customer;
        rental.movie = updatedRental.movie;
        rental.rentalDate = updatedRental.rentalDate;
        rental.returnDate = updatedRental.returnDate;
        rentalRepository.persist(rental);
        return Response.ok(rental).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRental(@PathParam("id") Long id) {
        Rental rental = rentalRepository.findById(id);
        if (rental != null) {
            rentalRepository.delete(rental);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
