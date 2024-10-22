package peaksoft.springbootlibrary.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class BookResponse {

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

    private LocalDate addedDate;

    private String coverImage;

    private float rating;

    private String language;

    private int pageCount;

}
