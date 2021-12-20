package com.usa.reto2back.servicio;

import com.usa.reto2back.modelo.Usuario;
import com.usa.reto2back.repositorio.UsuarioCrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioCrudRepositorio usuarioCrudRepositorio;

    public List<Usuario> getUsuarios() {
        return usuarioCrudRepositorio.findAll();
    }

    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioCrudRepositorio.findByEmail(email);
    }

    public boolean getEmail(String email) {
        Boolean aBoolean = getUsuarioByEmail(email).map(usuario -> {
            usuarioCrudRepositorio.findByEmail(email);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public Usuario getEmailAndPassword(String email, String password) {
        Usuario u = usuarioCrudRepositorio.findByEmailAndPassword(email, password);
        try {
            u.getId();
            return u;
        } catch (NullPointerException e) {
            Usuario u2 = new Usuario();
            u2.setId(null);
            u2.setIdentification(null);
            u2.setName(null);
            // u2.setBirthtDay(null);
            // u2.setMonthBirthtDay(null);
            u2.setAddress(null);
            u2.setCellPhone(null);
            u2.setEmail(null);
            u2.setPassword(null);
            u2.setZone(null);
            u2.setType(null);
            return u2;
        }
    }

    public Usuario saveUsuario(Usuario usuario) {
        if (usuario.getId() == null) {
            return usuarioCrudRepositorio.save(usuario);
        } else {
            Optional<Usuario> tmpUsuario = usuarioCrudRepositorio.findById(usuario.getId());
            if (tmpUsuario.isEmpty()) {
                return usuarioCrudRepositorio.save(usuario);
            } else {
                return usuario;
            }
        }
    }

    public Usuario updateUsuario(Usuario usuario) {
        if (usuario.getId() != null) {
            Optional<Usuario> u = usuarioCrudRepositorio.findById(usuario.getId());
            if (!u.isEmpty()) {
                if (usuario.getId() != null) {
                    u.get().setId(usuario.getId());
                }
                if (usuario.getIdentification() != null) {
                    u.get().setIdentification(usuario.getIdentification());
                }
                if (usuario.getName() != null) {
                    u.get().setName(usuario.getName());
                }
                if (usuario.getAddress() != null) {
                    u.get().setAddress(usuario.getAddress());
                }
                if (usuario.getCellPhone() != null) {
                    u.get().setCellPhone(usuario.getCellPhone());
                }
                if (usuario.getEmail() != null) {
                    u.get().setEmail(usuario.getEmail());
                }
                if (usuario.getPassword() != null) {
                    u.get().setPassword(usuario.getPassword());
                }
                if (usuario.getZone() != null) {
                    u.get().setZone(usuario.getZone());
                }
                if (usuario.getType() != null) {
                    u.get().setType(usuario.getType());
                }
                usuarioCrudRepositorio.save(u.get());
                return u.get();
            } else {
                return usuario;
            }
        } else {
            return usuario;
        }
    }

    public boolean deleteUsuario(int idUsuario) {
        Boolean aBoolean = usuarioCrudRepositorio.findById(idUsuario).map(usuario -> {
            usuarioCrudRepositorio.delete(usuario);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
