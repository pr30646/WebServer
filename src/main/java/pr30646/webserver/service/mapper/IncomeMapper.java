package pr30646.webserver.service.mapper;

import pr30646.webserver.model.Income;
import pr30646.webserver.service.dto.IncomeDto;

public class IncomeMapper {

    public static Income toEntity(IncomeDto incomeDto){
        Income income = new Income();
        income.setName(incomeDto.getName());
        income.setValue(incomeDto.getValue());
        income.setIncomeTime(incomeDto.getIncomeTime());
        return income;
    }
    public static Income toEntity(IncomeDto incomeDto, Long id){
        Income income = toEntity(incomeDto);
        income.setId(id);
        return income;
    }

    public static IncomeDto toDto(Income income){
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setName(income.getName());
        incomeDto.setIncomeTime(income.getIncomeTime());
        incomeDto.setValue(income.getValue());
        return incomeDto;
    }
}
