package com.codewithtanishq.boook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookserviceTest {
@Mock
    private Bookrepository bookrepository;
@InjectMocks
    private Bookservice bookservice;
private Book book;
@BeforeEach
    void setUp() {
    MockitoAnnotations.initMocks(this);
    book = new Book();
    book.setId(1L);
    book.setTitle("Book Title");
    book.setAuthor("Jack Bauer");
    book.setPrice("99.9");
}
@Test
    void getsaveBooks() {
    when(bookrepository.save(book)).thenReturn(book);
    Book savedBook = bookservice.saveBook(book);
    assertEquals(book, savedBook);
    verify(bookrepository, times(1)).save(book);
}
    @Test
    void testGetBookById() {
        when(bookrepository.findById(1L)).thenReturn(Optional.of(book));
        Optional<Book> result = bookservice.getBookbyid(1L);
        assertTrue(result.isPresent());
        assertEquals("Book Title", result.get().getTitle());
        verify(bookrepository, times(1)).findById(1L);
    }
    @Test
    void testUpdateBook() {
        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setPrice("199.99");

        when(bookrepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookrepository.save(any(Book.class))).thenAnswer(i -> i.getArguments()[0]);

        Optional<Book> result = bookservice.updateBook(1L, updatedBook);

        assertTrue(result.isPresent());
        assertEquals("Updated Title", result.get().getTitle());
        verify(bookrepository).save(book);
    }
    @Test
    void testDeleteBook() {
        when(bookrepository.findById(1L)).thenReturn(Optional.of(book));

        Boolean result = bookservice.deleteBook(1L);

        assertTrue(result);
        verify(bookrepository).delete(book);
    }
    @Test
    void testDeleteBookNotFound() {
        when(bookrepository.findById(1L)).thenReturn(Optional.empty());

        Boolean result = bookservice.deleteBook(1L);

        assertFalse(result);
        verify(bookrepository, never()).delete(any());
    }



}

