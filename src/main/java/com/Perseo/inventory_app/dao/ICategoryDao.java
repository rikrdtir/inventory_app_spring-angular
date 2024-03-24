package com.Perseo.inventory_app.dao;

import com.Perseo.inventory_app.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDao extends CrudRepository<Category, Long> {

}
