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
import org.acme.movie_rental.repository.MovieRepository;
import org.acme.movie_rental.entity.Movie;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    MovieRepository movieRepository;

    @GET
    public Response getAllMovies() {
        return Response.ok(movieRepository.listAll()).build();
    }

    @POST
    public Response createMovie(Movie movie) {
        movieRepository.persist(movie);
        return Response.status(Response.Status.CREATED).entity(movie).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateMovie(@PathParam("id") Long id, Movie movie) {
        Movie existingMovie = movieRepository.findById(id);
        if (existingMovie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingMovie.title = movie.title;
        existingMovie.director = movie.director;
        existingMovie.genre = movie.genre;
        movieRepository.persist(existingMovie);
        return Response.ok(existingMovie).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") Long id) {
        Movie movie = movieRepository.findById(id);
        if (movie != null) {
            movieRepository.delete(movie);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
