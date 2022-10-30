package com.ironhack.lab406.services;

import com.ironhack.lab406.DTO.ProductDTO;
import com.ironhack.lab406.DTO.ProductQuantityDTO;
import com.ironhack.lab406.models.Department;
import com.ironhack.lab406.models.Product;
import com.ironhack.lab406.repositories.DepartmentRepository;
import com.ironhack.lab406.repositories.ProductRepository;
import com.ironhack.lab406.services.interfaces.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public Product addNewProduct(ProductDTO productDTO){
        Department department = departmentRepository.findById(productDTO.getDepartmentId()).get();
        Product product = new Product(department, productDTO.getName(), productDTO.getQuantity());
        return productRepository.save(product);
    }

    public Product decreaseQuantityProduct(ProductQuantityDTO productQuantityDTO){
        if (productQuantityDTO.getQuantity()>productRepository.findById(productQuantityDTO.getProductId()).get().getQuantity()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Quantity to decrease not allowed.");
        }
        Product product = productRepository.findById(productQuantityDTO.getProductId()).get();
        product.setQuantity(product.getQuantity()-productQuantityDTO.getQuantity());
        return productRepository.save(product);
    }

    public List<Product> getProductsByDepartment(Optional<Long> id){
        if (!id.isPresent()){
            return productRepository.findAll();
        }
        Department department = departmentRepository.findById(id.get()).get();
        return productRepository.findByDepartment(department);
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The id product is incorrect"));
    }
    public void deleteProduct(Long id){
        if (!productRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The id product is incorrect");
        }
        productRepository.deleteById(id);
    }

}
