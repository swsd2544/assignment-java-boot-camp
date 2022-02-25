package com.example.javaassignment.shoppingapi.controllers;

import com.example.javaassignment.shoppingapi.models.Merchant;
import com.example.javaassignment.shoppingapi.models.Product;
import com.example.javaassignment.shoppingapi.payload.request.CreateProductRequest;
import com.example.javaassignment.shoppingapi.payload.response.MessageResponse;
import com.example.javaassignment.shoppingapi.payload.response.ProductInfoResponse;
import com.example.javaassignment.shoppingapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getProducts(@RequestParam(required = false, name = "query") String query) {
        List<Product> productList = productService.getAllProducts(query);
        List<ProductInfoResponse> response = productList.stream()
                .map(product -> new ProductInfoResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getMerchant().getName(),
                        product.getPrice(),
                        product.getAmount()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(response.toArray(new ProductInfoResponse[response.size()]));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        try {
            Product product = productService.getProductById(id);
            if (product != null) {
                return ResponseEntity.ok().body(new ProductInfoResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getMerchant().getName(),
                        product.getPrice(),
                        product.getAmount()));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Please enter a valid id"));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        Product product = productService.createProduct(
                createProductRequest.getName(),
                createProductRequest.getDescription(),
                createProductRequest.getMerchantId(),
                createProductRequest.getPrice(),
                createProductRequest.getAmount());
        if (product != null) {
            return ResponseEntity.ok().body(new ProductInfoResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getMerchant().getName(),
                    product.getPrice(),
                    product.getAmount()));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Please enter a valid merchant id"));
    }


}
