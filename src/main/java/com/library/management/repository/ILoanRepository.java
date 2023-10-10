package com.library.management.repository;

import com.library.management.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoanRepository extends JpaRepository<Loan, Long> {
}
