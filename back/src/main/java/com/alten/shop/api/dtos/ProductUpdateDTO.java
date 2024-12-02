package com.alten.shop.api.dtos;

import com.alten.shop.api.entites.Product;
import com.alten.shop.api.enums.InventoryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDTO {
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private Long shellId;
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    private double rating;

    public Product mapToEntity() {
        return Product.builder()
                .code(code)
                .name(name)
                .description(description)
                .image(image)
                .category(category)
                .price(price)
                .quantity(quantity)
                .internalReference(internalReference)
                .shellId(shellId)
                .inventoryStatus(inventoryStatus)
                .rating(rating)
                .build();
    }

}
