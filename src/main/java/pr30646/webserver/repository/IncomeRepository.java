package pr30646.webserver.repository;

import org.springframework.data.repository.CrudRepository;
import pr30646.webserver.model.Income;

public interface IncomeRepository extends CrudRepository<Income,Long> {
}
