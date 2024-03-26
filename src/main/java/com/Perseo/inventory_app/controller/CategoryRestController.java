package com.Perseo.inventory_app.controller;

import com.Perseo.inventory_app.model.Category;
import com.Perseo.inventory_app.response.CategoryResponseRest;
import com.Perseo.inventory_app.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
    @Autowired
    private ICategoryService service;
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategory(){
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoryById(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response;
    }
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category){
        ResponseEntity<CategoryResponseRest> response = service.save(category);
        return response;
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.update(category,id);
        return response;
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.deleteById(id);
        return response;
    }

}
