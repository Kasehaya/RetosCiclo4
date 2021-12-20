package com.usa.reto2back.repositorio;

import com.usa.reto2back.modelo.CleaningProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CleaningProductCrudRepositorio extends MongoRepository<CleaningProduct, String> {



}
