package com.ironhack.lab406.controllers.interfaces;

import com.ironhack.lab406.DTO.ProductDTO;
import com.ironhack.lab406.DTO.ProductQuantityDTO;
import com.ironhack.lab406.models.Product;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface ProductControllerInterface {
    Product addNewProduct(@RequestBody ProductDTO productDTO);
    Product decreaseProductQuantity(@RequestBody ProductQuantityDTO productQuantityDTO);
    List<Product> getProducts(@RequestBody Optional<Long> id);
    Product getProductById(@RequestBody Long id);
    void deleteProductById(@RequestBody Long id);
}
