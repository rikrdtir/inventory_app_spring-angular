package com.Perseo.inventory_app.dao;

import com.Perseo.inventory_app.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductDao extends CrudRepository<Product, Long> {
}
