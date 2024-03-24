package com.Perseo.inventory_app.services;

import com.Perseo.inventory_app.dao.ICategoryDao;
import com.Perseo.inventory_app.model.Category;
import com.Perseo.inventory_app.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryDao categoryDao;


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest response = new CategoryResponseRest();
        try {

            List<Category> categories = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategories(categories);
            response.setMetadata("Respuesta ok", "00","Respuesta exitosa");
        }catch (Exception e){

            response.setMetadata("Respuesta no ok", "-1","Ocurrio un error");
            e.getStackTrace();

            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}
