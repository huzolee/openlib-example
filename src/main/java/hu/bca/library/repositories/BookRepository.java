package hu.bca.library.repositories;

import hu.bca.library.models.Book;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Long> {

  @Query(
      value =
          "SELECT DISTINCT b.* "
              + "FROM Book b "
              + "JOIN book_author ba ON b.id = ba.book_id "
              + "JOIN Author a ON ba.author_id = a.id "
              + "WHERE a.country LIKE :authorCountry "
              + "AND (:from IS NULL OR b.year >= :from) "
              + "AND (:to IS NULL OR b.year <= :to) "
              + "ORDER BY b.year ASC",
      nativeQuery = true)
  Collection<Book> findAllBooksByFilters(
      @Param("authorCountry") String authorCountry,
      @Param("from") Integer from,
      @Param("to") Integer to);
}
