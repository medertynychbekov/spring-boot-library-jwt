package peaksoft.springbootlibrary.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private int year;

    private int availableCopies;

    private int totalCopies;

    private int totalBorrows;

    private String category;

    private String isbn;

    private String description;;

    private String publisher;;

    @CreationTimestamp
    private LocalDate addedDate;

    private String coverImage;

    private float rating;

    private String language;

    private int pageCount;

}
