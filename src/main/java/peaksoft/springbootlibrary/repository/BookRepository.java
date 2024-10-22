package peaksoft.springbootlibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.springbootlibrary.model.BookEntity;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("FROM BookEntity b WHERE b.author = :titleOrAuthor OR b.title = :titleOrAuthor")
    BookEntity findByTitleOrAuthor(String titleOrAuthor);

    List<BookEntity> findAllByOrderByTotalBorrowsDesc();

    @Modifying
    @Query("DELETE FROM BookEntity")
    void deleteAllBooks();
}
