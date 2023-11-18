package com.example.graphqlrefimpl;


import com.example.graphqlrefimpl.model.Book;
import com.example.graphqlrefimpl.model.Publisher;
import com.example.graphqlrefimpl.model.Review;
import com.example.graphqlrefimpl.service.BookService;
import com.example.graphqlrefimpl.service.PublisherService;
import graphql.GraphQLError;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class GraphQLController {

    private final PublisherService publisherService;
    private final BookService bookService;


    @GetMapping
    public String home(){
        return "redirect:/index.html";
    }

    @QueryMapping("allPublishers")
    public List<Publisher> getAllPublishers(){
        return publisherService.getAll();
    }

    @QueryMapping("getPublisher")
    public Publisher getPublisher(@Argument int publisherId){
        return publisherService.getById(publisherId)
                .orElseThrow(()->new IllegalArgumentException("No publisher found with id "+publisherId));
    }

    @QueryMapping("allBooks")
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }
    @QueryMapping("getBook")
    public Book getPublisher(@Argument UUID bookId) {
        return bookService.getById(bookId).orElseThrow(()->new IllegalArgumentException("No book found with id "+bookId));
    }
    @MutationMapping("addReview")
    public Book addReview(@Argument UUID bid, @Argument Review reviewInput){
        return bookService.addReview(bid, reviewInput);
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(Exception ex) {
        return GraphQLError.newError().errorType(ErrorType.BAD_REQUEST).message(ex.getMessage()).build();
    }
}
