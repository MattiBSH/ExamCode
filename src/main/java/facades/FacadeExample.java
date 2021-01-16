package facades;

import DTO.BookingDTO;
import DTO.HotelDTO;
import DTO.HotelResults;
import DTO.OneDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Booking;
import entities.Hotel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;
import utils.HttpUtils;

public class FacadeExample {

    
    private static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    private static FacadeExample instance;

    public FacadeExample() {
    }

    public static FacadeExample getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }
    
    public String fetchHotels() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<HotelDTO> hotels = new ArrayList();
        String url = "http://exam.cphdat.dk:8000/hotel/all";
        String raw = HttpUtils.fetchData(url);        
        return raw;
    }
    public void add(BookingDTO b){
        EntityManager e = emf.createEntityManager();
        e.getTransaction().begin();
        e.persist(new Booking(b.getNumberOfNights(),b.getPrice(),b.getName()));
        e.getTransaction().commit();
        
    }
    public ArrayList<BookingDTO>getAll(String name){
        EntityManager e = emf.createEntityManager();
        e.getTransaction().begin();
         TypedQuery<Booking> query = e.createQuery(
        "SELECT c FROM Booking c WHERE c.name = '" + name + "'",
        Booking.class);

        e.getTransaction().commit();
        ArrayList<BookingDTO>list= new ArrayList<>();
        for (Booking b : query.getResultList()) {
            
            list.add(new BookingDTO(b));
        }
        return  list;
        
    }
    public static void main(String[] args) throws IOException {
        FacadeExample f = new FacadeExample();
        BookingDTO b = new BookingDTO(1,1,"ookokok");
        f.add(b);
        System.out.println(f.getAll("matti"));
        
    }
}
