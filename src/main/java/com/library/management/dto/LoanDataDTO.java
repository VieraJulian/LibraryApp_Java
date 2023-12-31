package com.library.management.dto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDataDTO {

    private Long id_loan;
    private Long book_id;
    private String title;
    private Long user_id;
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDate startDate;
    private LocalDate endDate;

}
