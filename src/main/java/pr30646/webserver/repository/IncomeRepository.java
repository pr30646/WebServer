package pr30646.webserver.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pr30646.webserver.model.Income;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IncomeRepository extends CrudRepository<Income,Long> {
    @Query("select sum(income.value) from Income income")
    Optional<Double> getSumOfValue();

    @Query("select sum(income.value) from Income income where (income.incomeTime>= :from_time and income.incomeTime<= :to_time)")
    Optional<Double> getSumOfValue(@Param("from_time") LocalDateTime from, @Param("to_time") LocalDateTime to);

}
