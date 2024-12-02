package com.alten.shop.api.services.impl;

import com.alten.shop.api.dtos.PaginationServerResponse;
import com.alten.shop.api.dtos.ProductCreateDTO;
import com.alten.shop.api.dtos.ProductUpdateDTO;
import com.alten.shop.api.entites.Product;
import com.alten.shop.api.exceptions.EntityNotFoundException;
import com.alten.shop.api.repositories.ProductRepository;
import com.alten.shop.api.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public PaginationServerResponse<Product> getAllProducts(Pageable pageable) {
        var data = productRepository.findAll(pageable);
        return new PaginationServerResponse<>(data.getContent(), data.getTotalElements(), pageable.getPageNumber(), data.getTotalPages(), pageable.getPageSize());
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(ProductCreateDTO product) {
        var entity = product.mapToEntity();
        return productRepository.save(entity);
    }

    @Override
    public void deleteProduct(Long id) {
        var product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        product.ifPresent(productRepository::delete);
    }

    @Override
    public Product updateProduct(Long id, ProductUpdateDTO product) {
        var productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            var dbEntity = productOptional.get();
            BeanUtils.copyProperties(product, dbEntity);
            log.info("Updating product : {} form {}", dbEntity, product);
            return productRepository.save(dbEntity);
        }
        throw new EntityNotFoundException("Product not found with id: " + id);
    }
}
