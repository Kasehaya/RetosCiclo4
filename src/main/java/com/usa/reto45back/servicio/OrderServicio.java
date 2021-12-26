package com.usa.reto45back.servicio;

import com.usa.reto45back.modelo.Order;
import com.usa.reto45back.repositorio.OrderCrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * @author desarrolloextremo
 */
@Service
public class OrderServicio {

    @Autowired
    private OrderCrudRepositorio orderCrudRepositorio;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Order> getOrders() {
        return orderCrudRepositorio.findAll();
    }

    public Optional<Order> getOrder(int idOrder) {
        return orderCrudRepositorio.findById(idOrder);
    }

    public Order saveOrder(Order order) {
        if (order.getId() == null) {
            return order;
        } else {
            return orderCrudRepositorio.save(order);
        }
    }

    public Order updateOrder(Order order) {

        if (order.getId() != null) {
            Optional<Order> orderDb = orderCrudRepositorio.findById(order.getId());
            if (!orderDb.isEmpty()) {
                if (order.getStatus() != null) {
                    orderDb.get().setStatus(order.getStatus());
                }
                orderCrudRepositorio.save(orderDb.get());
                return orderDb.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public boolean deleteOrder(int id) {
        Boolean aBoolean = getOrder(id).map(order -> {
            orderCrudRepositorio.delete(order);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    //Ordenes de pedido asociadas a los asesores de una zona
    public List<Order> findByZone(String zona) {
        return orderCrudRepositorio.findByZone(zona);
    }

    //Reto 4: Ordenes de un asesor
    public List<Order> ordersSalesManByID(int idSalesMan) {
        Query query = new Query();
        Criteria dateCriteria = Criteria.where("salesMan.id").is(idSalesMan);
        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }

    //Reto 4: Ordenes de un asesor x Fecha
    public List<Order> ordersSalesManByDate(String dateStr, int id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Query query = new Query();
        Criteria dateCriteria = Criteria.where("registerDay").gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay()).lt(LocalDate.parse(dateStr, dtf).plusDays(2).atStartOfDay()).and("salesMan.id").is(id);
        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }

    //Reto 4: Ordenes de un asesor x Estado
    public List<Order> ordersSalesManByState(String state, Integer id) {
        Query query = new Query();
        Criteria dateCriteria = Criteria.where("salesMan.id").is(id).and("status").is(state);
        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }
}
