package peaksoft.springbootlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.springbootlibrary.dto.BookRequest;
import peaksoft.springbootlibrary.dto.BookResponse;
import peaksoft.springbootlibrary.mapper.BookMapper;
import peaksoft.springbootlibrary.model.BookEntity;
import peaksoft.springbootlibrary.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@RestControllerAdvice
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Transactional
    public BookResponse save(BookRequest bookRequest) {
        BookEntity bookEntity = bookMapper.mapToBookEntity(bookRequest);
        String isbn = generateISBN();
        bookEntity.setIsbn(isbn);
        //isbn сгенирорвть и присвоить значение к сущности bookEntity
        bookEntity = bookRepository.save(bookEntity);
        BookResponse bookResponse = bookMapper.mapToBookResponse(bookEntity);
        return bookResponse;
    }

    public List<BookResponse> findAll() {
        List<BookEntity> bookEntityList = bookRepository.findAll();
        List<BookResponse> bookResponseList = new ArrayList<>();
        for (BookEntity bookEntity : bookEntityList) {
            BookResponse bookResponse = bookMapper.mapToBookResponse(bookEntity);
            bookResponseList.add(bookResponse);
        }
        return bookEntityList
                .stream()
                .map(bookMapper::mapToBookResponse)
                .toList();
    }

    public BookResponse findByTitleOrAuthor(String title) {
        BookEntity bookEntity = bookRepository.findByTitleOrAuthor(title);
        if (bookEntity == null) {
            return null;
        }
        return bookMapper.mapToBookResponse(bookEntity);
    }

    public BookResponse updateById(Long id, BookRequest bookRequest) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        bookEntity.setTitle(bookRequest.getTitle());
        bookEntity.setAuthor(bookRequest.getAuthor());
        bookEntity.setYear(bookRequest.getYear());
        bookEntity.setAvailableCopies(bookRequest.getAvailableCopies());
        bookEntity.setTotalCopies(bookRequest.getTotalCopies());
        bookEntity.setTotalBorrows(bookRequest.getTotalBorrows());
        bookEntity.setCategory(bookRequest.getCategory());
        bookEntity.setDescription(bookRequest.getDescription());
        bookEntity.setPublisher(bookRequest.getPublisher());
        bookEntity.setCoverImage(bookRequest.getCoverImage());
        bookEntity.setRating(bookRequest.getRating());
        bookEntity.setLanguage(bookRequest.getLanguage());
        bookEntity.setPageCount(bookRequest.getPageCount());
        bookRepository.save(bookEntity);
        return bookMapper.mapToBookResponse(bookEntity);
    }

    public BookResponse deleteById(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        BookResponse bookResponse = bookMapper.mapToBookResponse(bookEntity);
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return bookResponse;
        } else {
            return null;
        }
    }

    public BookResponse findById(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity == null) {
            return null;
        }
        return bookMapper.mapToBookResponse(bookEntity);
    }

    public BookResponse borrowBooks(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);

        if (bookEntity == null) {
            throw new RuntimeException("Book with id: " + id + " not found");
        }

        if (bookEntity.getAvailableCopies() <= 0) {
            throw new RuntimeException("Not available copies ");
        }

        bookEntity.setAvailableCopies(bookEntity.getAvailableCopies() - 1);
        bookEntity.setTotalBorrows(bookEntity.getTotalBorrows() + 1);
        bookRepository.save(bookEntity);

        return bookMapper.mapToBookResponse(bookEntity);
    }

    public BookResponse booksIdReturn(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity == null) {
            return null;
        }
        bookEntity.setAvailableCopies(bookEntity.getAvailableCopies() + 1);
        bookRepository.save(bookEntity);
        return bookMapper.mapToBookResponse(bookEntity);
    }

    public List<BookResponse> popularBooks() {
        List<BookEntity> frequentlyBorrowedBooks = bookRepository.findAllByOrderByTotalBorrowsDesc();
        return frequentlyBorrowedBooks.stream()
                .map(bookMapper::mapToBookResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void clearDatabase() {
        bookRepository.deleteAllBooks();
    }


    private String generateISBN() {
        Random random = new Random();
        StringBuilder isbn = new StringBuilder("978");
        for (int i = 0; i < 10; i++) {
            isbn.append(random.nextInt(10));
        }
        return isbn.toString();
    }
}

