package com.codewithtanishq.boook;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class Bookservice {

    private final Bookrepository bookrepository;

    public Book saveBook(Book book) {
        return bookrepository.save(book);
    }

    public Optional<Book> getBookbyid(Long id) {
        return bookrepository.findById(id);
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return bookrepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPrice(updatedBook.getPrice());
            return bookrepository.save(book);
        });
    }

    public Boolean deleteBook(Long id) {
        return bookrepository.findById(id).map(book -> {
            bookrepository.delete(book);
            return true;

        }).orElse(false);
    }
}

