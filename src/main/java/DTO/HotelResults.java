/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author matti
 */
public class HotelResults {
    private HotelDTO[] results;

    public HotelResults(String results) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        this.results = gson.fromJson(results, HotelDTO[].class);
    }

    public HotelDTO[] getResults() {
        return results;
    }

    public void setResults(HotelDTO[] results) {
        this.results = results;
    }

    
    
    
    

}
