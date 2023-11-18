package com.example.graphqlrefimpl.service;


import com.example.graphqlrefimpl.model.Author;
import com.example.graphqlrefimpl.model.Book;
import com.example.graphqlrefimpl.model.Review;
import com.example.graphqlrefimpl.repo.AuthorRepo;
import com.example.graphqlrefimpl.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    public Book addBook(Book book){
        List<Author> authors = book.getAuthors();
        if(!CollectionUtils.isEmpty(authors)){
            authors = authorRepo.saveAll(book.getAuthors());
        }
        book.setAuthors(authors);
        return bookRepo.save(book);
    }



    public Book addReview(UUID bid, Review review){
        Book book = getById(bid).orElseThrow(()->new IllegalArgumentException("No book found with id "+bid));
        List<Review> reviews = book.getReviews();
        reviews.add(review);
        return bookRepo.save(book);
    }




    @Transactional(readOnly = true)
    public List<Book> getAll(){
        return bookRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Book> getById(UUID bid){
        return bookRepo.findById(bid);
    }



}
