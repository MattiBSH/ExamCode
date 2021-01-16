package facades;

import DTO.HotelDTO;
import DTO.HotelResults;
import DTO.OneDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Hotel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        String url = "https://exam.cphdat.dk/hotel/all";
        String raw = HttpUtils.fetchData(url);        
        return raw;
    }
    public static void main(String[] args) throws IOException {
        FacadeExample f = new FacadeExample();
        System.out.println(f.fetchHotels());
    }
}
