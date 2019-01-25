package pr30646.webserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pr30646.webserver.repository.ExpenseRepository;
import pr30646.webserver.repository.IncomeRepository;
import pr30646.webserver.service.AccountService;

import java.time.LocalDateTime;
@Service
public class AccountServiceImpl implements AccountService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public AccountServiceImpl(IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Double getAccountBalance() {
        Double expenseTotal = expenseRepository.getSumOfValue().orElse(0D);
        Double incomeTotal = incomeRepository.getSumOfValue().orElse(0D);
        return incomeTotal-expenseTotal;
    }

    @Override
    public Double getAccountBalance(LocalDateTime from, LocalDateTime to) {
        Double expenseTotal = expenseRepository.getSumOfValue(from,to).orElse(0D);
        Double incomeTotal = incomeRepository.getSumOfValue(from,to).orElse(0D);
        return incomeTotal-expenseTotal;
    }
}
