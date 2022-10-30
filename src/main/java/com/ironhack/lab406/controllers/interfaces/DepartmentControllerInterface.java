package com.ironhack.lab406.controllers.interfaces;

import com.ironhack.lab406.models.Department;
import org.springframework.web.bind.annotation.RequestBody;

public interface DepartmentControllerInterface {
    Department addNewDepartment(@RequestBody String name);
}
