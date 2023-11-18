package com.example.graphqlrefimpl.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name="author")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"books"})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;
    private String name;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL ,mappedBy = "authors")
    private List<Book> books;

}
