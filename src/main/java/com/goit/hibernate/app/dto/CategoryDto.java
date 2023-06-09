package com.goit.hibernate.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private List<ProductDto> products;
}
