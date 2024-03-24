package com.Perseo.inventory_app.response;

import com.Perseo.inventory_app.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private List<Category> categories;
}
