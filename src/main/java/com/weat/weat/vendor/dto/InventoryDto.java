package com.weat.weat.vendor.dto;

import com.weat.weat.vendor.model.Product;

import java.util.List;

public class InventoryDto {
    private Integer id;

    private List<ProductDto> productDtoList;

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




}
