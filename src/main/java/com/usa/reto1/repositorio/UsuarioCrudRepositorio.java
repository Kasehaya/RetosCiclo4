package com.usa.reto1.repositorio;

import com.usa.reto1.modelo.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioCrudRepositorio extends CrudRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM usuario WHERE email = ?", nativeQuery = true)
    public Optional<Usuario> findByEmail(String email);

    @Query(value = "SELECT * FROM usuario WHERE email = ?1 and password = ?2", nativeQuery = true)
    public Usuario findByEmailPass(String email, String pass);

}
