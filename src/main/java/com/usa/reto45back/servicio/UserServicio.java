package com.usa.reto45back.servicio;


import com.usa.reto45back.modelo.User;
import com.usa.reto45back.repositorio.UserCrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicio {

    @Autowired
    private UserCrudRepositorio userCrudRepositorio;

    public List<User> getUsers() {
        return userCrudRepositorio.findAll();
    }

    public Optional<User> getUser(int id) {
        return userCrudRepositorio.findById(id);
    }

    public Optional<User> getUsuarioByEmail(String email) {
        return userCrudRepositorio.findByEmail(email);
    }

    public boolean getEmail(String email) {
        Boolean aBoolean = getUsuarioByEmail(email).map(user -> {
            userCrudRepositorio.findByEmail(email);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public User getEmailAndPassword(String email, String password) {
        User u = userCrudRepositorio.findByEmailAndPassword(email, password);
        try {
            u.getId();
            return u;
        } catch (NullPointerException e) {
            User u2 = new User();
            return u2;
        }
    }

    public List<User> getbirthtDayList(String monthBirthtDay) {
        return userCrudRepositorio.findByMonthBirthtDay(monthBirthtDay);
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            return userCrudRepositorio.save(user);
        } else {
            Optional<User> tmpUser = userCrudRepositorio.findById(user.getId());
            if (tmpUser.isEmpty()) {
                return userCrudRepositorio.save(user);
            } else {
                return user;
            }
        }
    }

    public User updateUser(User user) {
        if (user.getId() != null) {
            Optional<User> u = userCrudRepositorio.findById(user.getId());
            if (!u.isEmpty()) {
                if (user.getIdentification() != null) {
                    u.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    u.get().setName(user.getName());
                }
                if (user.getAddress() != null) {
                    u.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    u.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    u.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    u.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    u.get().setZone(user.getZone());
                }
                userCrudRepositorio.save(u.get());
                return u.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    public boolean deleteUser(int idUser) {
        Boolean aBoolean = getUser(idUser).map(user -> {
            userCrudRepositorio.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
