package com.example.graphqlrefimpl.service;


import com.example.graphqlrefimpl.model.Publisher;
import com.example.graphqlrefimpl.repo.PublisherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepo publisherRepo;
    public Publisher addPublisher(Publisher publisher){
        return publisherRepo.save(publisher);
    }


    @Transactional(readOnly = true)
    public List<Publisher> getAll(){
        return publisherRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Publisher> getById(Integer id){
        return publisherRepo.findById(id);
    }

}
