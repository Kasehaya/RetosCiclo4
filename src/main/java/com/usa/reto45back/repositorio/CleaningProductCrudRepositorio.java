package com.usa.reto45back.repositorio;

import com.usa.reto45back.modelo.CleaningProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CleaningProductCrudRepositorio extends MongoRepository<CleaningProduct, String> {

    public List<CleaningProduct> findByPriceLessThanEqual(double precio);

    public List<CleaningProduct> findByCategory(String categoria);

    @Query("{'name':{'$regex':'?0','$options':'i'}}")
    public List<CleaningProduct> findByNameLike(String name);

    //Reto 5
    @Query("{'description':{'$regex':'?0','$options':'i'}}")
    public List<CleaningProduct> findByDescriptionLike(String description);

}
