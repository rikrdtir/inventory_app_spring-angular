package com.Perseo.inventory_app.response;

import com.Perseo.inventory_app.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
private List<Product> products;

}
