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
    public LoanDataDTO updateLoan(Long id, LoanDTO loanDTO) {
        Loan loanFound = loanRepo.findById(id).orElse(null);

        if(loanFound == null) {
            return null;
        }

        Book book = bookRepo.findById(loanDTO.getBook_id()).orElse(null);
        UserEntity user = userRepo.findById(loanDTO.getUser_id()).orElse(null);
        LocalDate endDate = loanDTO.getStartDate().plusMonths(1);

        loanFound.setStartDate(loanDTO.getStartDate());
        loanFound.setEndDate(endDate);
        loanFound.setBook(book);
        loanFound.setUser(user);

        loanRepo.save(loanFound);

        return LoanDataDTO.builder()
                .id_loan(loanFound.getId())
                .book_id(loanFound.getBook().getId())
                .title(loanFound.getBook().getTitle())
                .user_id(loanFound.getUser().getId())
                .username(loanFound.getUser().getUsername())
                .email(loanFound.getUser().getEmail())
                .phoneNumber(loanFound.getUser().getPhoneNumber())
                .startDate(loanFound.getStartDate())
                .endDate(loanFound.getEndDate())
                .build();
    }

    @Override
    public LoanDataDTO getLoan(Long id){
        Loan loan = loanRepo.findById(id).orElse(null);

        if(loan == null) {
            return null;
        }

        return LoanDataDTO.builder()
                .id_loan(loan.getId())
                .book_id(loan.getBook().getId())
                .title(loan.getBook().getTitle())
                .user_id(loan.getUser().getId())
                .username(loan.getUser().getUsername())
                .email(loan.getUser().getEmail())
                .phoneNumber(loan.getUser().getPhoneNumber())
                .startDate(loan.getStartDate())
                .endDate(loan.getEndDate())
                .build();
    }

    @Override
    public void deleteLoan(Long id) {
        loanRepo.deleteById(id);
    }
}
