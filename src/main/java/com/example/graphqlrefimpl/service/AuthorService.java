package com.example.graphqlrefimpl.service;


import com.example.graphqlrefimpl.model.Author;
import com.example.graphqlrefimpl.repo.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepo authorRepo;
    public List<Author> addAuthors(Collection<Author> authors){
        return authorRepo.saveAll(authors);
    }
    public Author addAuthor(Author author){
        return authorRepo.save(author);
    }




    @Transactional(readOnly = true)
    public List<Author> getAllAuthors(){
        return authorRepo.findAll();
    }

}
