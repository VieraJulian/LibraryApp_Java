package com.library.management.service;

import com.library.management.dto.LoanDTO;
import com.library.management.dto.LoanDataDTO;
import com.library.management.model.Loan;

public interface ILoanService {

    public LoanDataDTO createLoan(LoanDTO loanDTO);

    public LoanDataDTO updateLoan(Long id, LoanDTO loanDTO);

    public LoanDataDTO getLoan(Long id);

    public void deleteLoan(Long id);
}
