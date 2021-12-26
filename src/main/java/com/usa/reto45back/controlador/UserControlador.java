package com.usa.reto45back.controlador;

import com.usa.reto45back.modelo.User;
import com.usa.reto45back.servicio.UserServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserControlador {

    @Autowired
    private UserServicio userServicio;

    @GetMapping("/all")
    public List<User> getUsers() {
        return userServicio.getUsers();
    }

    @GetMapping("/{idUser}")
    public Optional<User> getUser(@PathVariable("idUser") int idUser) {
        return userServicio.getUser(idUser);
    }

    @GetMapping("/emailexist/{email}")
    public boolean getEmail(@PathVariable("email") String email) {
        return userServicio.getEmail(email);
    }

    @GetMapping("/{email}/{password}")
    public User getEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
        return userServicio.getEmailAndPassword(email, password);
    }

    @GetMapping("/birthday/{month}")
    public List<User> getbirthtDayList(@PathVariable("month") String monthBirthtDay) {
        return userServicio.getbirthtDayList(monthBirthtDay);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User user) {
        return userServicio.saveUser(user);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@RequestBody User user) {
        return userServicio.updateUser(user);
    }

    @DeleteMapping("/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteUser(@PathVariable("idUser") int idUser) {
        return userServicio.deleteUser(idUser);
    }

}
