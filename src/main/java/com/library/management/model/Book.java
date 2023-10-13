package com.library.management.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int yearOfPublication;
    private String author;

    @OneToOne(mappedBy = "book")
    private Loan loan;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;

}
