package hu.bca.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"rentals", "authors"})
    private List<Book> books;
}
