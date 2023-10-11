package com.library.management.dto;

import com.library.management.model.Loan;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDataDTO {

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String role;
    private List<Loan> loans;
}
