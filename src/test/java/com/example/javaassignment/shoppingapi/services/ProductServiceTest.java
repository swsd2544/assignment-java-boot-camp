package com.example.javaassignment.shoppingapi.services;

import com.example.javaassignment.shoppingapi.models.Merchant;
import com.example.javaassignment.shoppingapi.models.Product;
import com.example.javaassignment.shoppingapi.repository.MerchantRepository;
import com.example.javaassignment.shoppingapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MerchantRepository merchantRepository;

    @Test
    void getAllProducts() {
        Product product = new Product("name", "description", 10.0F, 1);
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.getAllProducts(null);
        assertInstanceOf(List.class, actualProducts);
        assertEquals(productRepository.findAll(), actualProducts);
    }

    @Test
    void getAllProductsWithQuery() {
        Product product = new Product("name", "description", 10.0F, 1);
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.getAllProducts("name");
        assertInstanceOf(List.class, actualProducts);
        assertEquals(productRepository.findAll(), actualProducts);
    }

    @Test
    void getAllProductsWithQueryButNone() {
        Product product = new Product("name", "description", 10.0F, 1);
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> products = productService.getAllProducts("test");
        assertInstanceOf(List.class, products);
        assertEquals(new ArrayList<Product>(), products);
    }

    @Test
    void getProductById() throws Exception {
        Product expectedProduct = new Product("name", "description", 10.0F, 1);
        expectedProduct.setId(9999L);
        when(productRepository.findById(9999L)).thenReturn(Optional.of(expectedProduct));

        Product actualProduct = productService.getProductById("9999");
        assertEquals(productRepository.findById(9999L).get(), actualProduct);
    }

    @Test
    void getProductByIdButFalseId() throws Exception {
        Product actualProduct = productService.getProductById("10000");
        assertNull(actualProduct);
    }

    @Test
    void getProductByIdButNotSendLong() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            productService.getProductById("test");
        });

        String expectedMessage = "ID is not in number format";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createProduct() {
        Merchant merchant = new Merchant("test company");
        merchant.setId(9999L);
        Product expectedProduct = new Product("name", "description", 10.0F, 1);
        expectedProduct.setId(9999L);
        expectedProduct.setMerchant(merchant);
        when(merchantRepository.findById(9999L)).thenReturn(Optional.of(merchant));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(expectedProduct);

        Product createdProduct = productService.createProduct(expectedProduct.getName(), expectedProduct.getDescription(),
                merchant.getId(), expectedProduct.getPrice(), expectedProduct.getAmount());
        assertEquals(expectedProduct, createdProduct);
    }

    @Test
    void createProductButFalseMerchantId() {
        Product product = new Product("new product", null, 10.0F, 10);
        Product createdProduct = productService.createProduct(product.getName(), product.getDescription(), 10000L, product.getPrice(), product.getAmount());
        assertNull(createdProduct);
    }
}