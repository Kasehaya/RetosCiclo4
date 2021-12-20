package com.usa.reto2back.servicio;

import com.usa.reto2back.modelo.CleaningProduct;
import com.usa.reto2back.repositorio.CleaningProductCrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CleaningProductServicio {

    @Autowired
    public CleaningProductCrudRepositorio cleaningProductCrudRepositorio;

    public List<CleaningProduct> getCleaningProducts() {
        return cleaningProductCrudRepositorio.findAll();
    }

    public CleaningProduct saveCleaningProduct(CleaningProduct cleaningProduct) {
        if (cleaningProduct.getReference() == null) {
            return cleaningProductCrudRepositorio.save(cleaningProduct);
        } else {
            Optional<CleaningProduct> tmpCleaningProduct = cleaningProductCrudRepositorio.findById(cleaningProduct.getReference());
            if (tmpCleaningProduct.isEmpty()) {
                return cleaningProductCrudRepositorio.save(cleaningProduct);
            } else {
                return cleaningProduct;
            }
        }
    }

    public CleaningProduct updateCleaningProduct(CleaningProduct cleaningProduct) {
        if (cleaningProduct.getReference() != null) {
            Optional<CleaningProduct> c = cleaningProductCrudRepositorio.findById(cleaningProduct.getReference());
            if (!c.isEmpty()) {
                if (cleaningProduct.getReference() != null) {
                    c.get().setReference(cleaningProduct.getReference());
                }
                if (cleaningProduct.getBrand() != null) {
                    c.get().setBrand(cleaningProduct.getBrand());
                }
                if (cleaningProduct.getCategory() != null) {
                    c.get().setCategory(cleaningProduct.getCategory());
                }
                if (cleaningProduct.getDescription() != null) {
                    c.get().setDescription(cleaningProduct.getDescription());
                }
                c.get().setAvailability(cleaningProduct.isAvailability());
                c.get().setPrice(cleaningProduct.getPrice());
                c.get().setQuantity(cleaningProduct.getQuantity());
                if (cleaningProduct.getPhotography() != null) {
                    c.get().setPhotography(cleaningProduct.getPhotography());
                }
                cleaningProductCrudRepositorio.save(cleaningProduct);
                return c.get();
            } else {
                return cleaningProduct;
            }
        } else {
            return cleaningProduct;
        }
    }

    public boolean deleteCleaningProduct(String reference) {
        Boolean aBoolean = cleaningProductCrudRepositorio.findById(reference).map(cleaningProduct -> {
            cleaningProductCrudRepositorio.delete(cleaningProduct);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
