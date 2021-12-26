package com.usa.reto45back.servicio;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.usa.reto45back.modelo.CleaningProduct;
import com.usa.reto45back.repositorio.CleaningProductCrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CleaningProductServicio {

    @Autowired
    private CleaningProductCrudRepositorio cleaningProductCrudRepositorio;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<CleaningProduct> getCleaningProducts() {
        return cleaningProductCrudRepositorio.findAll();
    }

    public Optional<CleaningProduct> getCleaningProduct(String reference) {
        return cleaningProductCrudRepositorio.findById(reference);
    }

    // Reto 5
    public List<CleaningProduct> getByPrice(double price) {
        return cleaningProductCrudRepositorio.findByPriceLessThanEqual(price);
    }

    public List<CleaningProduct> getByCategory(String category) {
        return cleaningProductCrudRepositorio.findByCategory(category);
    }

    public List<CleaningProduct> getByName(String name) {
        return cleaningProductCrudRepositorio.findByNameLike(name);
    }

    public List<String> getAllCategories() {
        List<String> categoryList = new ArrayList<>();
        MongoCollection mongoCollection = mongoTemplate.getCollection("gadgets");
        DistinctIterable distinctIterable = mongoCollection.distinct("category", String.class);
        MongoCursor mongoCursor = distinctIterable.iterator();
        while (mongoCursor.hasNext()) {
            String category = (String) mongoCursor.next();
            categoryList.add(category);
        }
        return categoryList;
    }

    // Reto 5
    public List<CleaningProduct> getByDescription(String description) {
        return cleaningProductCrudRepositorio.findByDescriptionLike(description);
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
                if (cleaningProduct.getBrand() != null) {
                    c.get().setBrand(cleaningProduct.getBrand());
                }
                if (cleaningProduct.getCategory() != null) {
                    c.get().setCategory(cleaningProduct.getCategory());
                }
                if (cleaningProduct.getDescription() != null) {
                    c.get().setDescription(cleaningProduct.getDescription());
                }
                /**
                 if (cleaningProduct.getName() != null) {
                 c.get().setName(cleaningProduct.getName());
                 }
                 **/
                c.get().setAvailability(cleaningProduct.isAvailability());
                c.get().setPrice(cleaningProduct.getPrice());
                c.get().setQuantity(cleaningProduct.getQuantity());
                if (cleaningProduct.getPhotography() != null) {
                    c.get().setPhotography(cleaningProduct.getPhotography());
                }
                cleaningProductCrudRepositorio.save(c.get());
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
