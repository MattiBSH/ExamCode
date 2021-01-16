/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Many;
import entities.Hotel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matti
 */
public class OneDTO {  
    private int id;
    private String name;
    
    private List<Many> many;

    public OneDTO(List<Many> many, String name) {
        this.many = new ArrayList();
        this.name = name;
    }

    
    public OneDTO(Hotel one) {
        this.id=one.getId();
        this.many = one.getMany();
        this.name = one.getName();
    }
    
    public OneDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Many> getMany() {
        return many;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "entities.One[ id=" + id + " ]";
    }
}
