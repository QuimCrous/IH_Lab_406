package com.ironhack.lab406.controllers;

import com.ironhack.lab406.DTO.ProductDTO;
import com.ironhack.lab406.controllers.interfaces.DepartmentControllerInterface;
import com.ironhack.lab406.models.Department;
import com.ironhack.lab406.models.Product;
import com.ironhack.lab406.services.interfaces.DepartmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController implements DepartmentControllerInterface {
    @Autowired
    DepartmentServiceInterface departmentService;

    @PostMapping("/departments/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Department addNewDepartment(@RequestBody String name){
        return departmentService.addNewDepartment(name);
    }
}
