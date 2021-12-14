package com.usa.reto1.servicio;

import com.usa.reto1.modelo.Usuario;
import com.usa.reto1.repositorio.UsuarioCrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioCrudRepositorio usuarioCrudRepositorio;

    public List<Usuario> getUsuarios() {
        return (List<Usuario>) usuarioCrudRepositorio.findAll();
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

    public Usuario getEmailPass(String email, String pass) {
        Usuario u = usuarioCrudRepositorio.findByEmailPass(email, pass);
        try {
            u.getId();
            return u;
        } catch (NullPointerException e) {
            Usuario u2 = new Usuario();
            u2.setId(null);
            u2.setEmail(email);
            u2.setName("NO DEFINIDO");
            u2.setPassword(pass);
            return u2;
        }
    }

    public Usuario guardarUsuario(Usuario usuario) {
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
}
