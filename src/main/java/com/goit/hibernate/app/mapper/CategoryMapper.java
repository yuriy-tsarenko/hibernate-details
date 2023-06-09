package com.goit.hibernate.app.mapper;

import com.goit.hibernate.app.dto.CategoryDto;
import com.goit.hibernate.app.entity.CategoryEntity;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor(staticName = "instance")
public final class CategoryMapper implements Mapper<CategoryEntity, CategoryDto> {

    private final ProductMapper productMapper = ProductMapper.instance();

    @Override
    public CategoryEntity mapDtoToEntity(CategoryDto source) {
        if (isNull(source)) {
            return null;
        }
        CategoryEntity target = new CategoryEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setProducts(productMapper.mapDtoToEntity(source.getProducts()));
        return target;
    }

    @Override
    public CategoryDto mapEntityToDto(CategoryEntity source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        CategoryDto target = new CategoryDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setProducts(productMapper.mapEntityToDto(source.getProducts()));
        return target;
    }
}
