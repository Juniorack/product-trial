package com.alten.shop.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PaginationServerResponse<T> {
    private List<T> data;
    private long itemsTotal;
    private int currentPage;
    private int pageTotal;
    private int size;

    public PaginationServerResponse() {
        this.data = new ArrayList<>();
        this.itemsTotal = 0L;
        this.currentPage = 0;
        this.size = 0;
        this.pageTotal = 0;
    }
}
