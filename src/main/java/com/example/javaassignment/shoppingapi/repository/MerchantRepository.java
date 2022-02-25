package com.example.javaassignment.shoppingapi.repository;

import com.example.javaassignment.shoppingapi.models.Merchant;
import com.example.javaassignment.shoppingapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    Optional<Merchant> findByName(String name);
    Optional<Product[]> getProductByName(String name);
}
