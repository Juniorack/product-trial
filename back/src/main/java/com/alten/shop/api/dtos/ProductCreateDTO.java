package com.alten.shop.api.dtos;

import com.alten.shop.api.entites.Product;
import com.alten.shop.api.enums.InventoryStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String category;
    private double price;

    public Product mapToEntity() {
        return Product.builder()
                .name(name)
                .description(description)
                .category(category)
                .price(price)
                .createdAt(new Date())
                .updatedAt(new Date())
                .quantity(0)
                .rating(0)
                .inventoryStatus(InventoryStatus.OUTOFSTOCK)
                .build();
    }
}
