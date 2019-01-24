package pr30646.webserver.service;

import pr30646.webserver.model.Expense;
import pr30646.webserver.service.dto.ExpenseDto;
import pr30646.webserver.service.exception.CategoryNotFoundException;
import pr30646.webserver.service.exception.ExpenseNotFoundException;

import java.util.List;

public interface ExpenseService {
    Expense addExpense(ExpenseDto expenseDto) throws CategoryNotFoundException;
    void updateExpense(ExpenseDto expenseDto, Long id) throws ExpenseNotFoundException, CategoryNotFoundException;
    void deleteExpense(Long id) throws ExpenseNotFoundException;
    ExpenseDto getExpense(Long id) throws ExpenseNotFoundException;
    List<ExpenseDto> getAllExpenses();
}
