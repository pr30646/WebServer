package pr30646.webserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pr30646.webserver.model.Expense;
@Repository
public interface ExpenseRepository extends CrudRepository<Expense,Long> {

}
