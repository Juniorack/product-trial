package com.alten.shop.api.repositories;

import com.alten.shop.api.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
