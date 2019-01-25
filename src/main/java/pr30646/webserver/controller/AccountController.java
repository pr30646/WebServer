package pr30646.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pr30646.webserver.service.AccountService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    ResponseEntity<Double> getAccountBalance(){
        return ResponseEntity.ok(accountService.getAccountBalance());
    }

    @GetMapping("/detailed")
    ResponseEntity<Double> getAccountBalance(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        return ResponseEntity.ok(accountService.getAccountBalance(from,to));
    }
}
