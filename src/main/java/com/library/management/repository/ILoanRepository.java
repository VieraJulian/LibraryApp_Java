package com.library.management.repository;

import com.library.management.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByUserId(Long userId);
}
