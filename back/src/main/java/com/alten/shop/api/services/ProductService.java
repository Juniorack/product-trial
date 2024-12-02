package com.alten.shop.api.services;

import com.alten.shop.api.dtos.PaginationServerResponse;
import com.alten.shop.api.dtos.ProductCreateDTO;
import com.alten.shop.api.dtos.ProductUpdateDTO;
import com.alten.shop.api.entites.Product;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    PaginationServerResponse<Product> getAllProducts(Pageable pageable);
    Optional<Product> getProductById(Long id);
    Product saveProduct(ProductCreateDTO product);
    void deleteProduct(Long id);
    Product updateProduct(Long id, ProductUpdateDTO item);
}
