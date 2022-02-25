package com.example.javaassignment.shoppingapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Product", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String description;
    private Float price;
    private Integer amount;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    public Product() {
    }

    public Product(String name, String description, Float price, Integer amount) {
        this.name = name;
        this.description = description;
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

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(obj.getClass() == getClass() && ((Product) obj).getId() == getId()) {
            return true;
        }
        return false;
    }
}
