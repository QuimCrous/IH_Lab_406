package com.ironhack.lab406.services;

import com.ironhack.lab406.models.Department;
import com.ironhack.lab406.repositories.DepartmentRepository;
import com.ironhack.lab406.services.interfaces.DepartmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DepartmentService implements DepartmentServiceInterface {
    @Autowired
    DepartmentRepository departmentRepository;
    public Department addNewDepartment(String name){
        return departmentRepository.save(new Department(name,new ArrayList<>()));
    }
}
