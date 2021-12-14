package com.usa.reto1.controlador;

import com.usa.reto1.modelo.Usuario;
import com.usa.reto1.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/all")
    public List<Usuario> getUsuarios() {
        return usuarioServicio.getUsuarios();
    }

    @GetMapping("/{email}")
    public boolean actualizarUsuario(@PathVariable("email") String email) {
        return usuarioServicio.getEmail(email);
    }

    @GetMapping("/{email}/{pass}")
    public Usuario actualizarUsuario(@PathVariable("email") String email, @PathVariable("pass") String pass) {
        return usuarioServicio.getEmailPass(email, pass);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.guardarUsuario(usuario);
    }
}
