package org.acme.movie_rental.resource;

import jakarta.transaction.Transactional;
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
import org.acme.movie_rental.repository.GenreRepository;
import org.acme.movie_rental.entity.Genre;

@Path("/genres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenreResource {

    @Inject
    GenreRepository genreRepository;

    @GET
    public Response getAllGenres() {
        return Response.ok(genreRepository.listAll()).build();
    }

    @POST
    public Response createGenre(Genre genre) {
        genreRepository.persist(genre);
        return Response.status(Response.Status.CREATED).entity(genre).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateGenre(@PathParam("id") Long id, Genre updatedGenre) {
        Genre genre = genreRepository.findById(id);
        if (genre == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        genre.name = updatedGenre.name;
        genreRepository.persist(genre);
        return Response.ok(genre).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGenre(@PathParam("id") Long id) {
        Genre genre = genreRepository.findById(id);
        if (genre != null) {
            genreRepository.delete(genre);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
