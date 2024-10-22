package peaksoft.springbootlibrary.dto;

import lombok.Data;

@Data
public class BookRequest {

    private Long id;

    private String title;

    private String author;

    private int year;

    private int availableCopies;

    private int totalCopies;

    private int totalBorrows;

    private String category;

    private String description;

    private String publisher;

    private String coverImage;

    private float rating;

    private String language;

    private int pageCount;

}
