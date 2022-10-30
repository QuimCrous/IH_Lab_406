package com.ironhack.lab406.controllers;

import com.ironhack.lab406.DTO.ProductDTO;
import com.ironhack.lab406.DTO.ProductQuantityDTO;
import com.ironhack.lab406.controllers.interfaces.ProductControllerInterface;
import com.ironhack.lab406.models.Department;
import com.ironhack.lab406.models.Product;
import com.ironhack.lab406.repositories.DepartmentRepository;
import com.ironhack.lab406.repositories.ProductRepository;
import com.ironhack.lab406.services.interfaces.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController implements ProductControllerInterface {
    @Autowired
    ProductServiceInterface productService;

    @Autowired
    ProductRepository productRepository;


    @PostMapping("/products/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody ProductDTO productDTO){
        return productService.addNewProduct(productDTO);
    }

    @PatchMapping("/products/decrease")
    @ResponseStatus(HttpStatus.OK)
    public Product decreaseProductQuantity(@RequestBody ProductQuantityDTO productQuantityDTO){
        return productService.decreaseQuantityProduct(productQuantityDTO);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts(@RequestBody Optional<Long> id){
        return productService.getProductsByDepartment(id);
    }

    @GetMapping("/productbyid")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@RequestBody Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/erase-product")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@RequestBody Long id){
        productService.deleteProduct(id);
    }
}
