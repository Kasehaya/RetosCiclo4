package com.usa.reto2back.controlador;

import com.usa.reto2back.modelo.Usuario;
import com.usa.reto2back.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/all")
    public List<Usuario> getUsuarios() {
        return usuarioServicio.getUsuarios();
    }

    @GetMapping("/{email}")
    public boolean getByEmail(@PathVariable("email") String email) {
        return usuarioServicio.getEmail(email);
    }

    @GetMapping("/{email}/{password}")
    public Usuario getEmailByPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
        return usuarioServicio.getEmailAndPassword(email, password);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario saveUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.saveUsuario(usuario);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.updateUsuario(usuario);
    }

    @DeleteMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteUsuario(@PathVariable("idUsuario") int idUsuario) {
        return usuarioServicio.deleteUsuario(idUsuario);
    }
}
