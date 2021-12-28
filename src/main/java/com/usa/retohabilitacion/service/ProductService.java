package com.usa.retohabilitacion.service;

import com.usa.retohabilitacion.model.Product;
import com.usa.retohabilitacion.repository.ProductCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    public List<Product> getProducts() {
        return productCrudRepository.findAll();
    }

    public Optional<Product> getProduct(String reference) {
        return productCrudRepository.findById(reference);
    }

    public Product saveProduct(Product product) {
        if (product.getReference() == null) {
            return productCrudRepository.save(product);
        } else {
            Optional<Product> tmpProduct = productCrudRepository.findById(product.getReference());
            if (tmpProduct.isEmpty()) {
                return productCrudRepository.save(product);
            } else {
                return product;
            }
        }
    }

    public Product updateProduct(Product product) {
        if (product.getReference() != null) {
            Optional<Product> p = productCrudRepository.findById(product.getReference());
            if (!p.isEmpty()) {
                if (product.getBrand() != null) {
                    p.get().setBrand(product.getBrand());
                }
                if (product.getCategory() != null) {
                    p.get().setCategory(product.getCategory());
                }
                if (product.getNombre() != null) {
                    p.get().setNombre(product.getNombre());
                }
                if (product.getDescription() != null) {
                    p.get().setDescription(product.getDescription());
                }
                if (product.getPrice() != 0.0) {
                    p.get().setPrice(product.getPrice());
                }
                if (product.getQuantity() != 0) {
                    p.get().setQuantity(product.getQuantity());
                }
                if (product.getPhotography() != null) {
                    p.get().setPhotography(product.getPhotography());
                }
                p.get().setAvailability(product.isAvailability());
                productCrudRepository.save(p.get());
                return p.get();
            } else {
                return product;
            }
        } else {
            return product;
        }
    }


    public boolean deleteProduct(String reference) {
        return getProduct(reference).map(product -> {
            productCrudRepository.delete(product);
            return true;
        }).orElse(false);
    }

    public List<Product> findByDescriptionContainingIgnoreCase(String description) {
        return productCrudRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<Product> findByPriceLessThanEqual(Double price) {
        return productCrudRepository.findByPriceLessThanEqual(price);
    }

}
