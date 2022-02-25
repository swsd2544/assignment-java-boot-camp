package com.example.javaassignment.shoppingapi.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CreateProductRequest {
    private String name;
    private String description;
    private Long merchantId;
    private Float price;
    private Integer amount;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String name, String description, Float price, Long merchantId, Integer amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.merchantId = merchantId;
        this.amount = amount;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
