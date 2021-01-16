package rest;

import DTO.BookingDTO;
import DTO.HotelDTO;
import DTO.OneDTO;
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
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
 
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
    @RolesAllowed("Admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookingsAdmin(@PathParam("name") String name ) throws IOException, InterruptedException, ExecutionException {
        ArrayList<BookingDTO> list =facade2.getAll(name);
        return GSON.toJson(list);
    }
}
