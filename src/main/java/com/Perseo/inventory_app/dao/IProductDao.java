package com.Perseo.inventory_app.dao;

import com.Perseo.inventory_app.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductDao extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> findByNameLike(String name);

    List<Product> findByNameContaining(String name);
    //List<Product> findByNameContainingIgnoreCase(String name);

}
