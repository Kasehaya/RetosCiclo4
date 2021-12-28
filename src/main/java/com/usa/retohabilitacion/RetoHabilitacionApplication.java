package com.usa.retohabilitacion;

import com.usa.retohabilitacion.repository.ProductCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetoHabilitacionApplication implements CommandLineRunner {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    public static void main(String[] args) {
        SpringApplication.run(RetoHabilitacionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productCrudRepository.deleteAll();
    }

}
