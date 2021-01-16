package rest;

import DTO.BookingDTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.FacadeExample;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import facades.FetchFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.SecurityContext;
 
/**
 * REST Web Service
 *
 * @author lam
 */
@Path("default")
public class DefaultResource {
    private final FetchFacade facade = new FetchFacade();
    private final FacadeExample facade2 = new FacadeExample();

    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

   @Context
    private SecurityContext context2;
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDefault() throws IOException, InterruptedException, ExecutionException {
        List<String> list = facade.fetchParallel();
        return GSON.toJson(list);
    }

    @Path("/hotels")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHotels() throws IOException, InterruptedException, ExecutionException {
        String list =facade2.fetchHotels();
        return GSON.toJson(list);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addBooking(String booking) throws IOException, InterruptedException, ExecutionException {
        BookingDTO bDTO = GSON.fromJson(booking, BookingDTO.class);        
        facade2.add(bDTO);
        return GSON.toJson(bDTO);
    }
    @Path("/bookings/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookings(@PathParam("name") String name ) throws IOException, InterruptedException, ExecutionException {
        ArrayList<BookingDTO> list =facade2.getAll(name);
        return GSON.toJson(list);
    }
    @Path("/bookingsad/{name}")
    @GET
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookingsAdmin(@PathParam("name") String name ) throws IOException, InterruptedException, ExecutionException {
        ArrayList<BookingDTO> list =facade2.getAll(name);
        return GSON.toJson(list);
    }
    
    @PUT
    @Path("/bookings")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String BookingsDEL(String booking) throws IOException, InterruptedException, ExecutionException {
        BookingDTO d=GSON.fromJson(booking, BookingDTO.class);
        facade2.edit(d);
        return GSON.toJson(d);
    }
    
    @DELETE
    @Path("/bookings/{nr}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookingsAdmin(@PathParam("nr") Long nr ) throws IOException, InterruptedException, ExecutionException {
        BookingDTO d =facade2.deleted(nr);
        return GSON.toJson(d);
    }
}
