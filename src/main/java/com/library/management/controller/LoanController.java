package com.library.management.controller;

import com.library.management.dto.LoanDTO;
import com.library.management.dto.LoanDataDTO;
import com.library.management.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private ILoanService loanServ;

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<LoanDataDTO> getLoan(@PathVariable Long id){
        try {
            LoanDataDTO loan = loanServ.getLoan(id);
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LoanDataDTO> createLoan(@RequestBody LoanDTO loanDTO){
        try {
            LoanDataDTO loanNew = loanServ.createLoan(loanDTO);
            return new ResponseEntity<>(loanNew, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LoanDataDTO> updateLoan(@PathVariable Long id, @RequestBody LoanDTO loanDTO){
        try {
            LoanDataDTO loanUpdated = loanServ.updateLoan(id, loanDTO);
            return new ResponseEntity<>(loanUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteLoan(@PathVariable Long id){
        try {
            loanServ.deleteLoan(id);
            return new ResponseEntity<>("Loan deleted success!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
