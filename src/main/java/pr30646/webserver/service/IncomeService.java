package pr30646.webserver.service;

import org.springframework.stereotype.Repository;
import pr30646.webserver.model.Income;
import pr30646.webserver.service.dto.IncomeDto;
import pr30646.webserver.service.exception.IncomeNotFoundException;

import java.util.List;

@Repository
public interface IncomeService {
    Income addIncome(IncomeDto incomeDto);
    void updateIncome(IncomeDto incomeDto, Long id) throws IncomeNotFoundException;
    void deleteIncome(Long id) throws IncomeNotFoundException;
    IncomeDto getIncome(Long id) throws IncomeNotFoundException;
    List<IncomeDto> getAllIncomes();
}
