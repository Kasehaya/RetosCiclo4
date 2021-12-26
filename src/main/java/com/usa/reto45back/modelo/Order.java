package com.usa.reto45back.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

/**
 * @author Hector Giraldo
 */

@Document(collection = "orders")
public class Order {

    public static String PENDING = "Pendiente";
    public static String APROVED = "Aprobada";
    public static String REJECTED = "Rechazada";

    @Id
    private Integer id;
    private Date registerDay;
    private String status;
    private User salesMan;

    private Map<Integer, CleaningProduct> products;
    private Map<Integer, Integer> quantities;

    public static String getPENDING() {
        return PENDING;
    }

    public static void setPENDING(String PENDING) {
        Order.PENDING = PENDING;
    }

    public static String getAPROVED() {
        return APROVED;
    }

    public static void setAPROVED(String APROVED) {
        Order.APROVED = APROVED;
    }

    public static String getREJECTED() {
        return REJECTED;
    }

    public static void setREJECTED(String REJECTED) {
        Order.REJECTED = REJECTED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(Date registerDay) {
        this.registerDay = registerDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(User salesMan) {
        this.salesMan = salesMan;
    }

    public Map<Integer, CleaningProduct> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, CleaningProduct> products) {
        this.products = products;
    }

    public Map<Integer, Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(Map<Integer, Integer> quantities) {
        this.quantities = quantities;
    }
}
