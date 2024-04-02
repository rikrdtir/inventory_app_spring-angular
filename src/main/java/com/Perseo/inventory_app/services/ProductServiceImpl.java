package com.Perseo.inventory_app.services;

import com.Perseo.inventory_app.dao.ICategoryDao;
import com.Perseo.inventory_app.dao.IProductDao;
import com.Perseo.inventory_app.model.Category;
import com.Perseo.inventory_app.model.Product;
import com.Perseo.inventory_app.response.ProductResponseRest;
import com.Perseo.inventory_app.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{
    private ICategoryDao categoryDao;
    private IProductDao productDao;

    public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
        super();
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            // Find Category to set ID in product
            Optional<Category> category = categoryDao.findById(categoryId);
            if (category.isPresent()){
                product.setCategory(category.get());
            }else {
                response.setMetadata("respuesta no ok","-1","No existe una categoría asociada al producto");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }
            // Save product in db
            Product productSaved = productDao.save(product);
            if (productSaved != null){
                list.add(productSaved);
                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok","00", "Producto guardado correctamente");
            } else {
                response.setMetadata("respuesta no ok","-1","No se ha podido guardar este producto");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);

            }

        } catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta no ok","-1","Error servidor, al guardar producto");

        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchById(Long id) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            // Search product by ID
            Optional<Product> product = productDao.findById(id);
            if (product.isPresent()){
                byte[] imagenDescompressed = Util.decompressZLib(product.get().getPicture());
                product.get().setPicture(imagenDescompressed);
                list.add(product.get());
                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok","00","Producto encontrado");

            }else {
                response.setMetadata("respuesta no ok","-1","Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta no ok","-1","Error al guardar producto");

        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchByName(String name) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {
            // Search product by name
            listAux = productDao.findByNameLike(name);

            if (listAux.size() > 0){
                listAux.stream().forEach((p) -> {
                    //byte[] imagenDescompressed = Util.decompressZLib(p.getPicture());
                    //p.setPicture(imagenDescompressed);
                    list.add(p);

                });

                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok","00","Productos encontrado");

            }else {
                response.setMetadata("respuesta no ok","-1","Productos no encontrados");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta no ok","-1","Error al buscar productos por nombre");

        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

    }
}
