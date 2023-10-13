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
    public BookResponseDTO updateBook(Long id, BookDTO bookDTO) throws IOException {
        Book bookFound = bookRepo.findById(id).orElse(null);

        if (bookFound == null) {
            return null;
        }

        byte[] bytes = bookDTO.getImage().getBytes();

        bookFound.setTitle(bookDTO.getTitle());
        bookFound.setAuthor(bookDTO.getAuthor());
        bookFound.setYearOfPublication(bookDTO.getYearOfPublication());
        bookFound.setImage(bytes);

        bookRepo.save(bookFound);

        String image = Base64.getEncoder().encodeToString(bytes);

        return BookResponseDTO.builder()
               .title(bookFound.getTitle())
               .author(bookFound.getAuthor())
               .yearOfPublication(bookFound.getYearOfPublication())
               .image(image)
               .build();
    }

    @Override
    public BookResponseDTO getBook(Long id) {
        Book bookFound = bookRepo.findById(id).orElse(null);

        if (bookFound == null) {
            return null;
        }

        String image = Base64.getEncoder().encodeToString(bookFound.getImage());

        return BookResponseDTO.builder()
                .title(bookFound.getTitle())
                .author(bookFound.getAuthor())
                .yearOfPublication(bookFound.getYearOfPublication())
                .image(image)
                .build();
    }

    @Override
    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }
}
