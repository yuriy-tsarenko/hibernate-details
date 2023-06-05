package com.goit.hibernate.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CustomerDto {

    private Long id;
    private String name;
    private String contactName;
    private String country;
}
