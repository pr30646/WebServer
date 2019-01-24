package pr30646.webserver.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pr30646.webserver.model.Category;
import pr30646.webserver.service.exception.CategoryNotFoundException;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    Optional<Category> findByName(String name) throws CategoryNotFoundException;
    boolean existsByName(String name);
}
