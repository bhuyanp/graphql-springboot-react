package com.example.graphqlrefimpl;

import com.example.graphqlrefimpl.model.Author;
import com.example.graphqlrefimpl.model.Book;
import com.example.graphqlrefimpl.model.Publisher;
import com.example.graphqlrefimpl.model.Review;
import com.example.graphqlrefimpl.repo.AuthorRepo;
import com.example.graphqlrefimpl.service.BookService;
import com.example.graphqlrefimpl.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.List;

@Slf4j
@SpringBootApplication
public class GraphQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQLApplication.class, args);
    }

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    AuthorRepo authorRepo;

    @Bean
    public CommandLineRunner getCommandLineRunner(PublisherService publisherService, BookService bookService) {
        return args -> {
            log.info("Application URL: http://localhost:{}/", serverPort);

            publisherService.addPublisher(Publisher.builder()
                    .name("Publisher A")
                    .city("NYC")
                    .state("NY")
                    .country("USA")
                    .build());
            publisherService.addPublisher(Publisher.builder()
                    .name("Publisher B")
                    .city("Piscataway")
                    .state("NJ")
                    .country("USA")
                    .build());
            publisherService.addPublisher(Publisher.builder()
                    .name("Publisher C")
                    .city("Jersey City")
                    .state("NJ")
                    .country("USA")
                    .build());

            publisherService.getAll().forEach(System.out::println);


            Book bookOne = bookService.addBook(Book.builder()
                    .title("Book One")
                    .description("Proident laboris velit nisi veniam qui labore laborum ipsum cupidatat.")
                    .releaseDate(Instant.now())
                    .publisher(Publisher.builder()
                            .name("Publisher 123")
                            .city("New Delhi")
                            .state("New Delhi")
                            .country("india")
                            .build()
                    )
                    .authors(List.of(
                            Author.builder()
                                    .name("PKB")
                                    .email("pkb@gmail.com")
                                    .build(),
                            Author.builder()
                                    .name("PKB11")
                                    .email("pkb11@gmail.com")
                                    .build()
                    ))
                    .build()
            );
            bookOne = bookService.addReview(bookOne.getBid(), Review.builder()
                    .reviewerName("John Doe")
                    .review("Cupidatat nisi ut voluptate deserunt magna adipiscing adipiscing cupidatat esse esse laborum. Non aliquip laboris consectetur fugiat laborum cillum aliqua sit esse. Aliqua in qui adipiscing non exercitation dolor incididunt qui aliqua commodo.")
                    .rating(5)
                    .build()
            );

            Book bookTwo = bookService.addBook(Book.builder()
                    .title("Book Two")
                    .description("Et dolore sed eu mollit ullamco tempor nisi sed fugiat.")
                    .releaseDate(Instant.now())
                    .publisher(Publisher.builder()
                            .name("Publisher ABC")
                            .city("Kolkata")
                            .state("West Bengal")
                            .country("india")
                            .build()
                    )
                    .authors(List.of(
                            Author.builder()
                                    .name("PKBhuyan")
                                    .email("pkbhuyan@gmail.com")
                                    .build()
                    ))
                    .build()
            );

            Book bookThree = bookService.addBook(Book.builder()
                    .title("Book Three")
                    .description("Nisi adipiscing in excepteur nulla labore incididunt culpa eiusmod occaecat.")
                    .releaseDate(Instant.now())
                    .publisher(Publisher.builder()
                            .name("Publisher XYZ")
                            .city("Seattle")
                            .state("Washington")
                            .country("USA")
                            .build()
                    )
                    .authors(List.of(
                            Author.builder()
                                    .name("John Doe")
                                    .email("jdoe@gmail.com")
                                    .build()
                            ))
                    .build()
            );


            System.out.println("Book"+bookOne);
        };
    }
}
