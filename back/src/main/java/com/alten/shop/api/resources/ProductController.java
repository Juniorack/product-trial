package com.alten.shop.api.resources;

import com.alten.shop.api.dtos.PaginationServerResponse;
import com.alten.shop.api.dtos.ProductCreateDTO;
import com.alten.shop.api.dtos.ProductUpdateDTO;
import com.alten.shop.api.entites.Product;
import com.alten.shop.api.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductCreateDTO item) {
        return ResponseEntity.ok(productService.saveProduct(item));
    }
    @GetMapping
    public ResponseEntity<PaginationServerResponse<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getAllProducts(Pageable.ofSize(size).withPage(page)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDTO item) {
        return ResponseEntity.ok(productService.updateProduct(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
