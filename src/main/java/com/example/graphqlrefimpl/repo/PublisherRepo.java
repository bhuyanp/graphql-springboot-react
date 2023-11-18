package com.example.graphqlrefimpl.repo;

import com.example.graphqlrefimpl.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Integer> {
}
