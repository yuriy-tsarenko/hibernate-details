package com.goit.hibernate.app.mapper;

import com.goit.hibernate.app.dto.ProductDto;
import com.goit.hibernate.app.entity.ProductEntity;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor(staticName = "instance")
public class ProductMapper implements Mapper<ProductEntity, ProductDto> {

    private final CustomerMapper customerMapper = CustomerMapper.instance();
    private final CategoryMapper categoryMapper = CategoryMapper.instance();


    @Override
    public ProductDto mapEntityToDto(ProductEntity source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        ProductDto target = new ProductDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setCustomer(customerMapper.mapEntityToDto(source.getCustomer()));
        target.setCategories(categoryMapper.mapEntityToDto(source.getCategories()));
        return target;
    }

    @Override
    public ProductEntity mapDtoToEntity(ProductDto source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        ProductEntity target = new ProductEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setCustomer(customerMapper.mapDtoToEntity(source.getCustomer()));
        target.setCategories(categoryMapper.mapDtoToEntity(source.getCategories()));
        return target;
    }
}
