package com.usa.reto45back.controlador;

import com.usa.reto45back.modelo.Order;
import com.usa.reto45back.servicio.OrderServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author desaextremo
 */
@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderControlador {

    @Autowired
    private OrderServicio orderServicio;

    @GetMapping("/all")
    public List<Order> getAll() {
        return orderServicio.getOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable("id") int id) {
        return orderServicio.getOrder(id);
    }

    //Reto 3:Ordenes de pedido asociadas a los asesores de una zona
    @GetMapping("/zona/{zona}")

    public List<Order> findByZone(@PathVariable("zona") String zona) {
        return orderServicio.findByZone(zona);
    }

    //Reto 4: Ordenes de un asesor
    @GetMapping("/salesman/{id}")
    public List<Order> ordersSalesManByID(@PathVariable("id") int id) {
        return orderServicio.ordersSalesManByID(id);
    }

    //Reto 4: Ordenes de un asesor x Fecha
    @GetMapping("/date/{date}/{id}")
    public List<Order> ordersSalesManByDate(@PathVariable("date") String date, @PathVariable("id") int id) {
        return orderServicio.ordersSalesManByDate(date, id);
    }

    //Reto 4: Ordenes de un asesor x Estado
    @GetMapping("/state/{state}/{id}")
    public List<Order> ordersSalesManByState(@PathVariable("state") String date, @PathVariable("id") int id) {
        return orderServicio.ordersSalesManByState(date, id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order gadget) {
        return orderServicio.saveOrder(gadget);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Order update(@RequestBody Order gadget) {
        return orderServicio.updateOrder(gadget);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return orderServicio.deleteOrder(id);
    }
}
