package com.ironhack.lab406.DTO;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductQuantityDTO {
    @NotNull
    @Min(1L)
    private Long quantity;
    @NotNull
    @Range(min = 1L,max = 6L)
    private Long productId;

    public ProductQuantityDTO(Long quantity, Long productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
