package pr30646.webserver.service.mapper;

import pr30646.webserver.model.Category;
import pr30646.webserver.service.dto.CategoryDto;

public class CategoryMapper {

    public static Category toEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setColor(categoryDto.getColor());
        return category;
    }
    public static Category toEntity(CategoryDto categoryDto, Long id){
        Category category = toEntity(categoryDto);
        category.setId(id);
        return category;
    }

    public static CategoryDto toDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setColor(category.getColor());
        return categoryDto;
    }

}
