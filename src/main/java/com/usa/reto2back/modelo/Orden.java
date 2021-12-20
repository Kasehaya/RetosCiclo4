package com.usa.reto2back.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.crypto.Data;
import java.util.Map;

@Document(collection = "orders")
public class Orden {

    public static String PENDING = "Pendiente";
    public static String APROVED = "Aprobada";
    public static String REJECTED = "Rechazada";

    @Id
    private Integer id;
    private Data registerDay;
    private String status;
    private Usuario salesMan;
    private Map<String, CleaningProduct> products;
    private Map<String, Integer> quantities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Data getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(Data registerDay) {
        this.registerDay = registerDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(Usuario salesMan) {
        this.salesMan = salesMan;
    }

    public Map<String, CleaningProduct> getProducts() {
        return products;
    }

    public void setProducts(Map<String, CleaningProduct> products) {
        this.products = products;
    }

    public Map<String, Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(Map<String, Integer> quantities) {
        this.quantities = quantities;
    }
}
