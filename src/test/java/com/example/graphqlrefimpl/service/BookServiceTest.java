package com.example.graphqlrefimpl.service;

import com.example.graphqlrefimpl.model.Author;
import com.example.graphqlrefimpl.model.Book;
import com.example.graphqlrefimpl.model.Review;
import com.example.graphqlrefimpl.repo.AuthorRepo;
import com.example.graphqlrefimpl.repo.BookRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepo bookRepo;
    @Mock
    private AuthorRepo authorRepo;

    @Mock
    Book book = new Book();
    @Mock
    Review review = new Review();


    @InjectMocks
    private BookService bookService;

    @Captor
    ArgumentCaptor<UUID> uuidArgCaptor;



    @Test
    void addBookWithAuthors() {

        //given
        given(book.getAuthors()).willReturn(List.of(Author.builder().aid(1).build()));

        //when
        bookService.addBook(book);

        //then
        verify(authorRepo).saveAll(book.getAuthors());
        verify(bookRepo).save(book);

    }

    @Test
    void addBookWithoutAuthors() {

        //given
        given(book.getAuthors()).willReturn(List.of());

        //when
        bookService.addBook(book);

        //then
        verify(bookRepo).save(book);

    }

    @Test
    void addReviewForMissingBook() {

        //given
        UUID uuid = UUID.randomUUID();
        //willReturn(Optional.empty()).given(bookService).getById(uuid);
        when(bookService.getById(uuid)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(()->bookService.addReview(uuid,review))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No book found with id "+uuid);

        verify(bookRepo,never()).save(any());
    }

    @Test
    void getAll() {
        //given

        //when
        bookService.getAll();

        //then
        verify(bookRepo).findAll();
    }

    @Test
    void getById() {
        //given
        UUID uuid = UUID.randomUUID();

        //when
        bookService.getById(uuid);

        //then
        verify(bookRepo).findById(uuidArgCaptor.capture());
        assertThat(uuidArgCaptor.getValue())
                .isNotNull()
                .isEqualTo(uuid);
    }
}