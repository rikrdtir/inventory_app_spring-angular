package com.Perseo.inventory_app.services;

import org.springframework.http.ResponseEntity;
import com.Perseo.inventory_app.model.Product;
import com.Perseo.inventory_app.response.ProductResponseRest;

public interface IProductService {
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);
    public ResponseEntity<ProductResponseRest> searchById(Long id);
    public ResponseEntity<ProductResponseRest> searchByName(String name);


}
