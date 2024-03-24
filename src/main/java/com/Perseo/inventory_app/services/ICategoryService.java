package com.Perseo.inventory_app.services;

import com.Perseo.inventory_app.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    public ResponseEntity<CategoryResponseRest> search();
}
