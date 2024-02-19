package hu.bca.library.controllers;

import hu.bca.library.repositories.AuthorRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
}
