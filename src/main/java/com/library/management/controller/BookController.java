package com.library.management.controller;

import com.library.management.dto.BookDTO;
import com.library.management.dto.BookResponseDTO;
import com.library.management.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookServ;

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<BookResponseDTO> createBook(@ModelAttribute BookDTO bookDTO) {
        try {
            BookResponseDTO newBook = bookServ.createBook(bookDTO);
            return new ResponseEntity<>(newBook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
