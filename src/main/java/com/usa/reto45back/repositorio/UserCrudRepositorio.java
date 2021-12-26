package com.usa.reto45back.repositorio;

import com.usa.reto45back.modelo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserCrudRepositorio extends MongoRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    // para seleccionar el usuario con el Id Maximo

    Optional<User> findTopByOrderByIdDesc();

    List<User> findBybirthtDay(Date date);

    List<User> findByMonthBirthtDay(String monthBirthtDay);

}
