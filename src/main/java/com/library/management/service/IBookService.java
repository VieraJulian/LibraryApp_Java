package com.library.management.service;

import com.library.management.dto.BookDTO;
import com.library.management.dto.BookResponseDTO;

import java.io.IOException;

public interface IBookService {

    public BookResponseDTO createBook(BookDTO bookDTO) throws IOException;

    public BookResponseDTO updateBook(Long id, BookDTO bookDTO) throws IOException;

    public BookResponseDTO getBook(Long id);

    public void deleteBook(Long id);
}
