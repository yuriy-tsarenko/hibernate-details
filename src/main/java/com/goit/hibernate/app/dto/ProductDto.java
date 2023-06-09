package com.goit.hibernate.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private CustomerDto customer;
    private List<CategoryDto> categories;
}
