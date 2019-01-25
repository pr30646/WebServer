package pr30646.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pr30646.webserver.model.Income;
import pr30646.webserver.service.dto.IncomeDto;
import pr30646.webserver.service.exception.IncomeNotFoundException;
import pr30646.webserver.service.impl.IncomeServiceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {

    private final IncomeServiceImpl incomeService;
    @Autowired
    public IncomeController(IncomeServiceImpl incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    public ResponseEntity<Void> addIncome(@RequestBody IncomeDto incomeDto){
        Income income = incomeService.addIncome(incomeDto);
        try{
            return ResponseEntity.created(new URI("/income/" + income.getId())).build();
        }catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateIncome(@RequestBody IncomeDto incomeDto, @PathVariable Long id){
        try{
            incomeService.updateIncome(incomeDto,id);
            return ResponseEntity.ok().build();
        }catch (IncomeNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<IncomeDto>> getAllIncomes(){
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeDto> getIncome(@PathVariable Long id){
        try {
            IncomeDto incomeDto = incomeService.getIncome(id);
            return ResponseEntity.ok(incomeDto);
        } catch (IncomeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id){
        try {
            incomeService.deleteIncome(id);
            return ResponseEntity.ok().build();
        } catch (IncomeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

