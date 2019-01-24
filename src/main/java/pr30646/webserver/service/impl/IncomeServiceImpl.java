package pr30646.webserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pr30646.webserver.model.Income;
import pr30646.webserver.repository.IncomeRepository;
import pr30646.webserver.service.IncomeService;
import pr30646.webserver.service.dto.IncomeDto;
import pr30646.webserver.service.exception.IncomeNotFoundException;
import pr30646.webserver.service.mapper.IncomeMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Income addIncome(IncomeDto incomeDto) {
        return incomeRepository.save(IncomeMapper.toEntity(incomeDto));
    }

    @Override
    public void updateIncome(IncomeDto incomeDto, Long id) throws IncomeNotFoundException {
        if (incomeRepository.existsById(id)) {
            incomeRepository.save(IncomeMapper.toEntity(incomeDto, id));
        } else throw new IncomeNotFoundException(id);
    }

    @Override
    public void deleteIncome(Long id) throws IncomeNotFoundException {
        Income income = incomeRepository.findById(id).orElseThrow(() -> new IncomeNotFoundException(id));
        incomeRepository.delete(income);
    }

    @Override
    public IncomeDto getIncome(Long id) throws IncomeNotFoundException {
        return incomeRepository.findById(id).map(IncomeMapper::toDto).orElseThrow(() -> new IncomeNotFoundException(id));
    }

    @Override
    public List<IncomeDto> getAllIncomes() {
        return StreamSupport.stream(incomeRepository.findAll().spliterator(), false).map(IncomeMapper::toDto).collect(Collectors.toList());

    }
}
