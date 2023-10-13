package com.library.management.service;

import com.library.management.dto.BookDTO;
import com.library.management.dto.BookResponseDTO;
import com.library.management.model.Book;
import com.library.management.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepo;

    @Override
    public BookResponseDTO createBook(BookDTO bookDTO) throws IOException {

        byte[] bytes = bookDTO.getImage().getBytes();

        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .yearOfPublication(bookDTO.getYearOfPublication())
                .image(bytes)
                .build();

        bookRepo.save(book);

        String image = Base64.getEncoder().encodeToString(bytes);

        return BookResponseDTO.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .yearOfPublication(book.getYearOfPublication())
                .image(image)
                .build();
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        return null;
    }

    @Override
    public BookDTO getBook(Long id) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {

    }
}
