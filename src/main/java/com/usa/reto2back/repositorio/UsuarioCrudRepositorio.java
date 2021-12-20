package com.usa.reto2back.repositorio;

import com.usa.reto2back.modelo.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioCrudRepositorio extends MongoRepository<Usuario, Integer> {

    public Optional<Usuario> findByEmail(String email);

    public Usuario findByEmailAndPassword(String email, String password);

}
