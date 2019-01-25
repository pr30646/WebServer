package pr30646.webserver.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pr30646.webserver.model.Expense;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    @Query("select sum(expense.value) from Expense expense")
    Optional<Double> getSumOfValue();

    @Query("select sum(expense.value) from Expense expense " +
            "where expense.expenseTime >= :from_time " +
            "and expense.expenseTime <= :to_time")
    Optional<Double> getSumOfValue(@Param("from_time") LocalDateTime from, @Param("to_time") LocalDateTime to);
}
