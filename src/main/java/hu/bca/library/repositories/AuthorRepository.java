package hu.bca.library.repositories;

import hu.bca.library.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}