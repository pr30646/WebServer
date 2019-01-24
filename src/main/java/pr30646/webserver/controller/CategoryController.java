package pr30646.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pr30646.webserver.model.Category;
import pr30646.webserver.service.impl.CategoryServiceImpl;
import pr30646.webserver.service.dto.CategoryDto;
import pr30646.webserver.service.exception.CategoryNotFoundException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Void> addCategory(@RequestBody CategoryDto categoryDto) {
        Category newCategory = categoryService.addCategory(categoryDto);
        try {
            return ResponseEntity.created(new URI("/category/" + newCategory.getId())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id){
        try {
            categoryService.updateCategory(categoryDto, id);
            return ResponseEntity.ok().build();
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){
        try {
            CategoryDto categoryDto = categoryService.getCategory(id);
            return ResponseEntity.ok(categoryDto);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        try{
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().build();
        } catch (CategoryNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
