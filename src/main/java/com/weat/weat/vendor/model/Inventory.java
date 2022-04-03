package com.weat.weat.vendor.model;

import com.weat.weat.vendor.dto.InventoryDto;
import com.weat.weat.vendor.dto.ProductDto;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name = "inventory")
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> productObjectList;

    public Inventory( InventoryDto inventoryDto){
        List<Product> productList= new ArrayList<Product>();// to store product object list
        for ( ProductDto pro: inventoryDto.getProductDtoList()){
            Product productObject = new Product(pro);
            productList.add(productObject);
        }
        this.setProductObjectList(productList);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProductObjectList() {
        return productObjectList;
    }

    public void setProductObjectList(List<Product> productObjectList) {
        this.productObjectList = productObjectList;
    }
}
