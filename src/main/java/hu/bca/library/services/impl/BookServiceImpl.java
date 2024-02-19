package hu.bca.library.services.impl;

import hu.bca.library.dto.BookInfoDTO;
import hu.bca.library.models.Author;
import hu.bca.library.models.Book;
import hu.bca.library.repositories.AuthorRepository;
import hu.bca.library.repositories.BookRepository;
import hu.bca.library.services.BookService;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookServiceImpl implements BookService {
    
    private static final String OPEN_LIBRARY_WORKS_ENDPOINT = "https://openlibrary.org/works/";
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final RestTemplate restTemplate;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Book addAuthor(Long bookId, Long authorId) {
        Optional<Book> book = this.bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with id %s not found", bookId));
        }
        Optional<Author> author = this.authorRepository.findById(authorId);
        if (author.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Author with id %s not found", authorId));
        }

        List<Author> authors = book.get().getAuthors();
        authors.add(author.get());

        book.get().setAuthors(authors);
        return this.bookRepository.save(book.get());
    }

  @Override
  public void updateAllWithYear() {
    final Iterable<Book> allBooks = bookRepository.findAll();
    allBooks.forEach(book -> book.setYear(getYearByWorkId(book.getWorkId())));
    bookRepository.saveAll(allBooks);
  }

    @Override
    public Collection<Book> getAllBooksByFilters(final String authorCountry, final Integer from, final Integer to) {
        return bookRepository.findAllBooksByFilters(authorCountry, from, to);
  }

  private Integer getYearByWorkId(final String workId) {
    final BookInfoDTO bookInfo = getBookInfoByWorkId(workId);
    final String firstPublishDate = bookInfo != null ? bookInfo.getFirstPublishDate() : null;

    if (firstPublishDate == null) {
      return null;
    }

    final String[] firstPublishDateParts = firstPublishDate.split(" ");

    return Arrays.stream(firstPublishDateParts)
        .filter(d -> d.matches("\\d{4}"))
        .map(Integer::parseInt)
        .findFirst()
        .orElse(null);
  }

  private BookInfoDTO getBookInfoByWorkId(final String workId) {
    ResponseEntity<BookInfoDTO> response = null;

    try {
      response =
          restTemplate.getForEntity(OPEN_LIBRARY_WORKS_ENDPOINT + workId + ".json", BookInfoDTO.class);
    } catch (final RestClientException ignore) {
    }

    if (response != null) {
      final HttpStatusCode statusCode = response.getStatusCode();

      if (statusCode.is2xxSuccessful()) {
        return response.getBody();
      }
    }

    return null;
  }
}
