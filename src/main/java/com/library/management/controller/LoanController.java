package com.library.management.controller;

import com.library.management.dto.LoanDTO;
import com.library.management.dto.LoanDataDTO;
import com.library.management.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private ILoanService loanServ;

    @GetMapping("/get/{id}")
    public ResponseEntity<LoanDataDTO> getLoan(@PathVariable Long id){
        try {
            LoanDataDTO loan = loanServ.getLoan(id);
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<LoanDataDTO> createLoan(@RequestBody LoanDTO loanDTO){
        try {
            LoanDataDTO loanNew = loanServ.createLoan(loanDTO);
            return new ResponseEntity<>(loanNew, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long id){
        try {
            loanServ.deleteLoan(id);
            return new ResponseEntity<>("Loan deleted success!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
