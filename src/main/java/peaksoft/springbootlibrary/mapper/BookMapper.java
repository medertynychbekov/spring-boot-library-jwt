package peaksoft.springbootlibrary.mapper;

import org.springframework.stereotype.Component;
import peaksoft.springbootlibrary.dto.BookRequest;
import peaksoft.springbootlibrary.dto.BookResponse;
import peaksoft.springbootlibrary.model.BookEntity;

@Component
public class BookMapper {

    public BookResponse mapToBookResponse(BookEntity bookEntity) {
        return new BookResponse()
                .setId(bookEntity.getId())
                .setTitle(bookEntity.getTitle())
                .setAuthor(bookEntity.getAuthor())
                .setYear(bookEntity.getYear())
                .setAvailableCopies(bookEntity.getAvailableCopies())
                .setTotalCopies(bookEntity.getTotalCopies())
                .setTotalBorrows(bookEntity.getTotalBorrows())
                .setCategory(bookEntity.getCategory())
                .setIsbn(bookEntity.getIsbn())
                .setDescription(bookEntity.getDescription())
                .setPublisher(bookEntity.getPublisher())
                .setAddedDate(bookEntity.getAddedDate())
                .setCoverImage(bookEntity.getCoverImage())
                .setRating(bookEntity.getRating())
                .setLanguage(bookEntity.getLanguage())
                .setPageCount(bookEntity.getPageCount());
    }

    public BookEntity mapToBookEntity(BookRequest bookRequest) {
        return new BookEntity()
                .setTitle(bookRequest.getTitle())
                .setAuthor(bookRequest.getAuthor())
                .setYear(bookRequest.getYear())
                .setAvailableCopies(bookRequest.getAvailableCopies())
                .setTotalCopies(bookRequest.getTotalCopies())
                .setTotalBorrows(bookRequest.getTotalBorrows())
                .setCategory(bookRequest.getCategory())
                .setDescription(bookRequest.getDescription())
                .setPublisher(bookRequest.getPublisher())
                .setCoverImage(bookRequest.getCoverImage())
                .setRating(bookRequest.getRating())
                .setLanguage(bookRequest.getLanguage())
                .setPageCount(bookRequest.getPageCount());


    }

//    public BookEntity mapToBookEntity(BookRequest bookRequest) {
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setTitle(bookRequest.getTitle());
//        bookEntity.setAuthor(bookRequest.getAuthor());
//        bookEntity.setYear(bookRequest.getYear());
//        bookEntity.setAvailableCopies(bookRequest.getAvailableCopies());
//        bookEntity.setTotalCopies(bookRequest.getTotalCopies());
//        bookEntity.setTotalBorrows(bookRequest.getTotalBorrows());
//        bookEntity.setCategory(bookRequest.getCategory());
//        bookEntity.setDescription(bookRequest.getDescription());
//        bookEntity.setPublisher(bookRequest.getPublisher());
//        bookEntity.setCoverImage(bookRequest.getCoverImage());
//        bookEntity.setRating(bookRequest.getRating());
//        bookEntity.setLanguage(bookRequest.getLanguage());
//        bookEntity.setPageCount(bookRequest.getPageCount());
//        return bookEntity;
//
//    }

//    public BookResponse mapToBookResponse(BookEntity bookEntity) {
//        BookResponse bookResponse = new BookResponse();
//        bookResponse.setId(bookEntity.getId());
//        bookResponse.setTitle(bookEntity.getTitle());
//        bookResponse.setAuthor(bookEntity.getAuthor());
//        bookResponse.setYear(bookEntity.getYear());
//        bookResponse.setAvailableCopies(bookEntity.getAvailableCopies());
//        bookResponse.setTotalCopies(bookEntity.getTotalCopies());
//        bookResponse.setTotalBorrows(bookEntity.getTotalBorrows());
//        bookResponse.setCategory(bookEntity.getCategory());
//        bookResponse.setIsbn(bookEntity.getIsbn());
//        bookResponse.setDescription(bookEntity.getDescription());
//        bookResponse.setPublisher(bookEntity.getPublisher());
//        bookResponse.setAddedDate(bookEntity.getAddedDate());
//        bookResponse.setCoverImage(bookEntity.getCoverImage());
//        bookResponse.setRating(bookEntity.getRating());
//        bookResponse.setLanguage(bookEntity.getLanguage());
//        bookResponse.setPageCount(bookEntity.getPageCount());
//        return bookResponse;
//    }
}
