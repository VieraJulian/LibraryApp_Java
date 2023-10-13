package com.library.management.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoanDataDTO {

    private Long id_loan;
    private Long book_id;
    private String book_title;
    private LocalDate startDate;
    private LocalDate endDate;
}
