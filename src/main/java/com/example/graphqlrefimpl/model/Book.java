package com.example.graphqlrefimpl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bid;
    private String title;
    private String description;
    private Instant releaseDate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pid_fk", referencedColumnName = "pid")
    private Publisher publisher;


    @ManyToMany
    @JoinTable(name = "AUTHOR_BOOK",
            joinColumns =
            @JoinColumn(name = "book_id", referencedColumnName = "bid"),
            inverseJoinColumns =
            @JoinColumn(name = "author_id", referencedColumnName = "aid")
    )
    private List<Author> authors;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bid_fk", referencedColumnName = "bid")
    private List<Review> reviews = new ArrayList<>();


}
