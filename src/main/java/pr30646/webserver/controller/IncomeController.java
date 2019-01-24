package pr30646.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pr30646.webserver.service.impl.IncomeServiceImpl;

@RestController
@RequestMapping("/income")
public class IncomeController {

    private final IncomeServiceImpl incomeService;
    @Autowired
    public IncomeController(IncomeServiceImpl incomeService) {
        this.incomeService = incomeService;
    }

    
}

