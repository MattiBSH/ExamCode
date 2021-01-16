/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Booking;
import entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


/**
 *
 * @author matti
 */
public class BookingDTO {
    private Long id;
    private java.util.Date date;
    private int numberOfNights;
    private int price;
    private String name;
    
    private List<User> users = new ArrayList<>();

        public BookingDTO( int numberOfNights, int price, String name) {
            
            this.numberOfNights = numberOfNights;
            this.price = price;
            this.name=name;
        }
    public BookingDTO(Booking b) {
            this.id=b.getId();
            this.date = b.getDate();
            this.numberOfNights = b.getNumberOfNights();
            this.price = b.getPrice();
            this.name=b.getName();
        }
    public BookingDTO(Long id,int numberOfNights, int price, String name) {
            this.id=id;
            
            this.numberOfNights = numberOfNights;
            this.price = price;
            this.name=name;
        }
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    }
