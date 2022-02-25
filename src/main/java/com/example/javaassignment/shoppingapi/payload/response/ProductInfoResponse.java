package com.example.javaassignment.shoppingapi.payload.response;

import java.util.List;

public class ProductInfoResponse {
    private Long id;
    private String name;
    private String description;
    private String merchantName;
    private Float price;
    private Integer amount;

    public ProductInfoResponse() {
    }

    public ProductInfoResponse(Long id, String name, String description, String merchantName, Float price, Integer amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.merchantName = merchantName;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
