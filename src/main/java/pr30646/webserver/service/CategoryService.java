package pr30646.webserver.service;

import pr30646.webserver.model.Category;
import pr30646.webserver.service.dto.CategoryDto;
import pr30646.webserver.service.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    Category addCategory(CategoryDto categoryDto);
    void updateCategory(CategoryDto categoryDto, Long id) throws CategoryNotFoundException;
    void deleteCategory(Long id) throws CategoryNotFoundException;
    CategoryDto getCategory(Long id) throws CategoryNotFoundException;
    List<CategoryDto> getAllCategories();
}
