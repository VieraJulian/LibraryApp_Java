package com.library.management.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String role;
}
