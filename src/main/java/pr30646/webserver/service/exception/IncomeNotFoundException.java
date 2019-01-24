package pr30646.webserver.service.exception;

public class IncomeNotFoundException extends Exception {

    public IncomeNotFoundException(Long id) {
        super("Could not find income with id: "+ id);
    }
}
