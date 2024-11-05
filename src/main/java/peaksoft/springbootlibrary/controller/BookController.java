package peaksoft.springbootlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootlibrary.dto.BookRequest;
import peaksoft.springbootlibrary.dto.BookResponse;
import peaksoft.springbootlibrary.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public BookResponse save(@RequestBody BookRequest bookRequest) {
        return bookService.save(bookRequest);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<BookResponse> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public BookResponse findByTitleOrAuthor(@RequestParam("title_or_author") String titleOrAuthor) {
        return bookService.findByTitleOrAuthor(titleOrAuthor);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BookResponse updateById(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        return bookService.updateById(id, bookRequest);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BookResponse deleteById(@PathVariable Long id) {
        return bookService.deleteById(id);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public BookResponse findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping("/{id}/borrow")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BookResponse borrowBook(@PathVariable Long id) {
        return bookService.borrowBooks(id);
    }

    @PostMapping("/{id}/return")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public BookResponse booksIdReturn(@PathVariable Long id) {
        return bookService.booksIdReturn(id);
    }

    @GetMapping("/popular")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<BookResponse>> popularBooks() {
        List<BookResponse> frequentlyBorrowedBooks = bookService.popularBooks();
        return ResponseEntity.ok(frequentlyBorrowedBooks);
    }

    @DeleteMapping("/delete-all-books")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void clearAllBooks() {
        bookService.clearDatabase();
    }

}


