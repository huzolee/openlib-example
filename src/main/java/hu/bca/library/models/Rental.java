package hu.bca.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnoreProperties({"rentals", "authors"})
    private Book book;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date endDate;
}
