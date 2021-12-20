package com.usa.reto2back.controlador;

import com.usa.reto2back.modelo.CleaningProduct;
import com.usa.reto2back.servicio.CleaningProductServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cleaningproduct")
public class CleaningProductControlador {

    @Autowired
    private CleaningProductServicio cleaningProductServicio;

    @GetMapping("/all")
    public List<CleaningProduct> getCleaningProducts() {
        return cleaningProductServicio.getCleaningProducts();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public CleaningProduct saveCleaningProduct(@RequestBody CleaningProduct cleaningProduct) {
        return cleaningProductServicio.saveCleaningProduct(cleaningProduct);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public CleaningProduct updateCleaningProduct(@RequestBody CleaningProduct cleaningProduct) {
        return cleaningProductServicio.updateCleaningProduct(cleaningProduct);
    }

    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteCleaningProduct(@PathVariable("reference") String reference) {
        return cleaningProductServicio.deleteCleaningProduct(reference);
    }
}
