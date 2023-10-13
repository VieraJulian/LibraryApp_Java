package com.library.management.dto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDTO {

    private LocalDate startDate;
    private Long book_id;
    private Long user_id;
}
