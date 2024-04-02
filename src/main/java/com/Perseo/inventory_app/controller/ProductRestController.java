package com.Perseo.inventory_app.controller;

import com.Perseo.inventory_app.model.Product;
import com.Perseo.inventory_app.response.ProductResponseRest;
import com.Perseo.inventory_app.services.IProductService;
import com.Perseo.inventory_app.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    private IProductService productService;

    public ProductRestController(IProductService productService) {
        //super();
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseRest> save(
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("account") int account,
            @RequestParam("categoryId") Long categoryId
            ) throws IOException {

        Product  product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setAccount(account);
        product.setPicture(Util.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = productService.save(product,categoryId);
        return response;
    }

}
