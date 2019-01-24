package pr30646.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pr30646.webserver.model.Expense;
import pr30646.webserver.service.impl.ExpenseServiceImpl;
import pr30646.webserver.service.dto.ExpenseDto;
import pr30646.webserver.service.exception.CategoryNotFoundException;
import pr30646.webserver.service.exception.ExpenseNotFoundException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseServiceImpl expenseService;

    @Autowired
    public ExpenseController(ExpenseServiceImpl expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Void> addExpense(@RequestBody ExpenseDto expenseDto) {
        try {
            Expense newExpense = expenseService.addExpense(expenseDto);
            return ResponseEntity.created(new URI("/expense/" + newExpense.getId())).build();
        } catch (CategoryNotFoundException | URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateExpense(@RequestBody ExpenseDto expenseDto, @PathVariable Long id) {
        try {
            expenseService.updateExpense(expenseDto, id);
            return ResponseEntity.ok().build();
        } catch (CategoryNotFoundException | ExpenseNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.ok().build();
        } catch (ExpenseNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(expenseService.getExpense(id));
        } catch (ExpenseNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
