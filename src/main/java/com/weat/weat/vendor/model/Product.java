package com.weat.weat.vendor.model;

import com.weat.weat.vendor.dto.ProductDto;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Table(name = "product_list")
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "main_category")
    private String mainCategory;

    @Column(name = "category")
    private String category;

    @Column(name = "sub_category")
    private String subCategory;

    @Column(name = "brand")
    private String brand;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Integer size;

    @Column(name = "unit")
    private String unit;

    @Column(name = "image_url")
    private String imageUrl;


    public Product(ProductDto productDto){
        this.setMainCategory(productDto.getMainCategory());
        this.setCategory(productDto.getCategory());
        this.setSubCategory(productDto.getSubCategory());
        this.setBrand(productDto.getBrand());
        this.setPackaging(productDto.getPackaging());
        this.setName(productDto.getName());
        this.setSize(productDto.getSize());
        this.setUnit(productDto.getUnit());
        this.setImageUrl(productDto.getImageUrl());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
