package pr30646.webserver.service.mapper;

import pr30646.webserver.model.Category;
import pr30646.webserver.model.Expense;
import pr30646.webserver.service.dto.ExpenseDto;

public class ExpenseMapper {
    public static Expense toEntity(ExpenseDto expenseDto, Category category){
        Expense  expense = new Expense();
        expense.setCategory(category);
        expense.setExpenseTime(expenseDto.getExpenseTime());
        expense.setName(expenseDto.getName());
        expense.setValue(expenseDto.getValue());
        return expense;
    }

    public static Expense toEntity(ExpenseDto expenseDto, Category category, Long id){
        Expense expense = toEntity(expenseDto,category);
        expense.setId(id);
        return expense;
    }

    public static ExpenseDto toDto(Expense expense){
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setCategoryName(expense.getCategory().getName());
        expenseDto.setExpenseTime(expense.getExpenseTime());
        expenseDto.setName(expense.getName());
        expenseDto.setValue(expense.getValue());
        return expenseDto;
    }
}
