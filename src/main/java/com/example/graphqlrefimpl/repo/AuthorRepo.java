package com.example.graphqlrefimpl.repo;

import com.example.graphqlrefimpl.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
