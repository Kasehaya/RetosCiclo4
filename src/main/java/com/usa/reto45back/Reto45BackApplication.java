package com.usa.reto45back;

import com.usa.reto45back.repositorio.CleaningProductCrudRepositorio;
import com.usa.reto45back.repositorio.OrderCrudRepositorio;
import com.usa.reto45back.repositorio.UserCrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Reto45BackApplication implements CommandLineRunner {

    @Autowired
    private UserCrudRepositorio userCrudRepositorio;
    @Autowired
    private CleaningProductCrudRepositorio cleaningProductCrudRepositorio;
    @Autowired
    private OrderCrudRepositorio orderCrudRepositorio;

    public static void main(String[] args) {
        SpringApplication.run(Reto45BackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userCrudRepositorio.deleteAll();
        cleaningProductCrudRepositorio.deleteAll();
        orderCrudRepositorio.deleteAll();
    }

}
