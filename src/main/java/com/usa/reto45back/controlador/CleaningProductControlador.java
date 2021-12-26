package com.usa.reto45back.controlador;

import com.usa.reto45back.modelo.CleaningProduct;
import com.usa.reto45back.servicio.CleaningProductServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cleaningproduct")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CleaningProductControlador {

    @Autowired
    private CleaningProductServicio cleaningProductServicio;

    @GetMapping("/all")
    public List<CleaningProduct> getCleaningProducts() {
        return cleaningProductServicio.getCleaningProducts();
    }

    @GetMapping("/{reference}")
    public Optional<CleaningProduct> getCleaningProduct(@PathVariable("reference") String reference) {
        return cleaningProductServicio.getCleaningProduct(reference);
    }

    @GetMapping("/category/{category}")
    public List<CleaningProduct> getByCategory(@PathVariable("category") String category) {
        return cleaningProductServicio.getByCategory(category);
    }

    // Reto 5
    @GetMapping("/price/{price}")
    public List<CleaningProduct> getByPrice(@PathVariable("price") double price) {
        return cleaningProductServicio.getByPrice(price);
    }

    @GetMapping("/name/{name}")
    public List<CleaningProduct> getByName(@PathVariable("name") String name) {
        return cleaningProductServicio.getByName(name);
    }

    @GetMapping("/category")
    public List<String> getAllCategories() {
        return cleaningProductServicio.getAllCategories();
    }

    // Reto 5
    @GetMapping("/description/{description}")
    public List<CleaningProduct> getByDescription(@PathVariable("description") String description) {
        return cleaningProductServicio.getByDescription(description);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public CleaningProduct saveCleaningProduct(@RequestBody CleaningProduct cleaningProduct) {
        return cleaningProductServicio.saveCleaningProduct(cleaningProduct);
    }

    @PutMapping("update")
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
