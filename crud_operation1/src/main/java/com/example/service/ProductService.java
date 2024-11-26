package com.example.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
@Service
public class ProductService {
@Autowired
ProductRepository productRepository;
@Autowired
CategoryRepository categoryRepository;
public Page<Product> getAllProducts(int page) {
    return productRepository.findAll(PageRequest.of(page - 1, 10));
}


public Product getProductById(int id) {
    return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
}

public Product createProduct(Product product) {
    Category category = categoryRepository.findById(product.getCategory().getId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
    product.setCategory(category);
    return productRepository.save(product);
}

public Product updateProduct(int id, Product updatedProduct) {
    Product product = getProductById(id);
    Category category = categoryRepository.findById(updatedProduct.getCategory().getId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
    product.setName(updatedProduct.getName());
    product.setCategory(category);
    return productRepository.save(product);
}

public void deleteProduct(int id) {
    productRepository.deleteById(id);
}
}


