package pr30646.webserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pr30646.webserver.model.Category;
import pr30646.webserver.model.Expense;
import pr30646.webserver.repository.CategoryRepository;
import pr30646.webserver.repository.ExpenseRepository;
import pr30646.webserver.service.ExpenseService;
import pr30646.webserver.service.dto.ExpenseDto;
import pr30646.webserver.service.exception.CategoryNotFoundException;
import pr30646.webserver.service.exception.ExpenseNotFoundException;
import pr30646.webserver.service.mapper.ExpenseMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public Expense addExpense(ExpenseDto expenseDto) throws CategoryNotFoundException {
        Category category = categoryRepository.findByName(expenseDto.getCategoryName()).orElseThrow(() -> new CategoryNotFoundException(expenseDto.getCategoryName()));

        Expense expense = expenseRepository.save(ExpenseMapper.toEntity(expenseDto, category));
        return expense;
    }

    public void updateExpense(ExpenseDto expenseDto, Long id) throws CategoryNotFoundException, ExpenseNotFoundException {
        Category category = categoryRepository.findByName(expenseDto.getName()).orElseThrow(() -> new CategoryNotFoundException(expenseDto.getCategoryName()));
        if (expenseRepository.existsById(id)) {
            expenseRepository.save(ExpenseMapper.toEntity(expenseDto, category, id));
        } else throw new ExpenseNotFoundException(id);
    }

    public void deleteExpense(Long id) throws ExpenseNotFoundException {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));
        expenseRepository.delete(expense);
    }

    public ExpenseDto getExpense(Long id) throws ExpenseNotFoundException {
        return expenseRepository.findById(id).map(ExpenseMapper::toDto).orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    public List<ExpenseDto> getAllExpenses() {
        return StreamSupport.stream(expenseRepository.findAll().spliterator(), false).map(ExpenseMapper::toDto).collect(Collectors.toList());
    }
}
