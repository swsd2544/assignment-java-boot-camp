package com.example.javaassignment.shoppingapi.services;

import com.example.javaassignment.shoppingapi.models.Merchant;
import com.example.javaassignment.shoppingapi.models.Product;
import com.example.javaassignment.shoppingapi.repository.MerchantRepository;
import com.example.javaassignment.shoppingapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    public List<Product> getAllProducts(String query) {
        List<Product> products = productRepository.findAll();
        if (query != null) {
            products = products.stream()
                    .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return products;
    }

    public Product getProductById(String strId) throws Exception {
        try {
            Long id = Long.parseLong(strId);
            Optional<Product> result = productRepository.findById(id);
            if (result.isPresent()) {
                return result.get();
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ID is not in number format");
        }
        return null;
    }

    public Product createProduct(String name, String description, Long merchantId, Float price, Integer amount) {
        Optional<Merchant> result = merchantRepository.findById(merchantId);
        if (result.isPresent()) {
            Merchant merchant = result.get();
            Product product = new Product(name, description, price, amount);
            product.setMerchant(merchant);
            return productRepository.save(product);
        }
        return null;
    }
}
