package pr30646.webserver.service;

import java.time.LocalDateTime;

public interface AccountService {
    Double getAccountBalance();
    Double getAccountBalance(LocalDateTime from, LocalDateTime to);
}
