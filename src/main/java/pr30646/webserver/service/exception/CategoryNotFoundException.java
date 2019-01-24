package pr30646.webserver.service.exception;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(String name) {
        super("Could not find Category with name " + name);
    }

    public CategoryNotFoundException(Long id) {
        super("Could not find Category with id " + id);
    }
}
