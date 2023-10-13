package com.library.management.controller;

import com.library.management.dto.LoanDTO;
import com.library.management.dto.LoanDataDTO;
import com.library.management.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private ILoanService loanServ;

    @PostMapping("/create")
    public ResponseEntity<LoanDataDTO> createLoan(@RequestBody LoanDTO loanDTO){
        try {
            LoanDataDTO loanNew = loanServ.createLoan(loanDTO);
            return new ResponseEntity<>(loanNew, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}