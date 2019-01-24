package pr30646.webserver.service.exception;

public class ExpenseNotFoundException  extends Exception{
    public ExpenseNotFoundException(Long id) {
        super("Could not find expense with id: "+ id);
    }
}
