package pr30646.webserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pr30646.webserver.model.Category;
import pr30646.webserver.repository.CategoryRepository;
import pr30646.webserver.service.CategoryService;
import pr30646.webserver.service.dto.CategoryDto;
import pr30646.webserver.service.exception.CategoryNotFoundException;
import pr30646.webserver.service.mapper.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(CategoryDto categoryDto) {
        return categoryRepository.save(CategoryMapper.toEntity(categoryDto));
    }

    public void updateCategory(CategoryDto categoryDto, Long id) throws CategoryNotFoundException {
        if (categoryRepository.existsById(id)) {
            categoryRepository.save(CategoryMapper.toEntity(categoryDto, id));
        } else throw new CategoryNotFoundException(id);
    }

    public void deleteCategory(Long id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.delete(category);
    }

    public CategoryDto getCategory(Long id) throws CategoryNotFoundException {
        CategoryDto categoryDto = categoryRepository.findById(id).map(CategoryMapper::toDto).orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryDto;
    }

    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryDto = StreamSupport.stream(categoryRepository.findAll().spliterator(), false).map(CategoryMapper::toDto).collect(Collectors.toList());
        return categoryDto;
    }
}
