package com.library.management.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

    private String title;
    private String author;
    private int yearOfPublication;
    private MultipartFile image;

}
