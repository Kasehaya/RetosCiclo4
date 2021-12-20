package com.usa.reto2back;

import com.usa.reto2back.repositorio.CleaningProductCrudRepositorio;
import com.usa.reto2back.repositorio.UsuarioCrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Reto2BackApplication implements CommandLineRunner {

    @Autowired
    private UsuarioCrudRepositorio usuarioCrudRepositorio;
    @Autowired
    private CleaningProductCrudRepositorio cleaningProductCrudRepositorio;

    public static void main(String[] args) {
        SpringApplication.run(Reto2BackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        usuarioCrudRepositorio.deleteAll();
        cleaningProductCrudRepositorio.deleteAll();
    }

}
