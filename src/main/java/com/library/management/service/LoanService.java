package com.library.management.service;

import com.library.management.dto.LoanDTO;
import com.library.management.dto.LoanDataDTO;
import com.library.management.model.Book;
import com.library.management.model.Loan;
import com.library.management.model.UserEntity;
import com.library.management.repository.IBookRepository;
import com.library.management.repository.ILoanRepository;
import com.library.management.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService implements ILoanService {

    @Autowired
    private ILoanRepository loanRepo;

    @Autowired
    private IBookRepository bookRepo;

    @Autowired
    private IUserRepository userRepo;

    @Override
    public LoanDataDTO createLoan(LoanDTO loanDTO) {

        LocalDate endDate = loanDTO.getStartDate().plusMonths(1);

        Book book = bookRepo.findById(loanDTO.getBook_id()).orElse(null);

        UserEntity user = userRepo.findById(loanDTO.getUser_id()).orElse(null);

        Loan loanNew = Loan.builder()
                .startDate(loanDTO.getStartDate())
                .endDate(endDate)
                .book(book)
                .user(user)
                .build();

        loanRepo.save(loanNew);

        return LoanDataDTO.builder()
                .id_loan(loanNew.getId())
                .book_id(loanNew.getBook().getId())
                .title(loanNew.getBook().getTitle())
                .user_id(loanNew.getUser().getId())
                .username(loanNew.getUser().getUsername())
                .email(loanNew.getUser().getEmail())
                .phoneNumber(loanNew.getUser().getPhoneNumber())
                .startDate(loanNew.getStartDate())
                .endDate(loanNew.getEndDate())
                .build();
    }

    @Override
    public LoanDataDTO updateLoan(Long id, Loan loan) {
        return null;
    }

    @Override
    public LoanDataDTO getLoan(Long id) {
        return null;
    }

    @Override
    public void deleteLoan(Long id) {

    }
}
