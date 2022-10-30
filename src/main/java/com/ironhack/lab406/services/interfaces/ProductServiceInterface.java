package com.ironhack.lab406.services.interfaces;

import com.ironhack.lab406.DTO.ProductDTO;
import com.ironhack.lab406.DTO.ProductQuantityDTO;
import com.ironhack.lab406.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    Product addNewProduct(ProductDTO productDTO);
    public Product decreaseQuantityProduct(ProductQuantityDTO productQuantityDTO);
    List<Product> getProductsByDepartment(Optional<Long> id);
    Product getProductById(Long id);
    void deleteProduct(Long id);
}
