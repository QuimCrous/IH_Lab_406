package com.ironhack.lab406.DTO;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDTO {
    @NotNull
    @Min(1L)
    private Long quantity;
    @NotEmpty
    private String name;
    @NotNull
    @Range(min = 1L,max = 6L)
    private Long departmentId;

    public ProductDTO(Long quantity, String name, Long departmentId) {
        this.quantity = quantity;
        this.name = name;
        this.departmentId = departmentId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
