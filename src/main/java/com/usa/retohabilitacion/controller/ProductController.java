package com.usa.retohabilitacion.controller;

import com.usa.retohabilitacion.model.Product;
import com.usa.retohabilitacion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{reference}")
    public Optional<Product> getProduct(@PathVariable("reference") String reference) {
        return productService.getProduct(reference);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteProduct(@PathVariable("reference") String reference) {
        return productService.deleteProduct(reference);
    }

    @GetMapping("/description/{description}")
    public List<Product> findByDescriptionContainingIgnoreCase(@PathVariable("description") String description) {
        return productService.findByDescriptionContainingIgnoreCase(description);
    }

    @GetMapping("/price/{price}")
    public List<Product> findByPriceLessThanEqual(@PathVariable("price") Double price) {
        return productService.findByPriceLessThanEqual(price);
    }
}