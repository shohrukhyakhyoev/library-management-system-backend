package com.iutlibrary.backend.bookStuff.bookItem;

import com.iutlibrary.backend.utility.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface BookItemRepository extends JpaRepository<BookItem, Long> {



    @Query("SELECT b FROM BookItem b WHERE b.ISBN = ?1 AND b.status = ?2")
    List<BookItem> findTopByISBNAndStatus(Long ISBN, BookStatus bookStatus);

    @Query("SELECT b From BookItem b WHERE b.status = ?1")
    List<BookItem> findBookItemByStatus(String status);

    @Query("SELECT b From BookItem b WHERE b.barcode = ?1")
    Optional<BookItem> findByBarcode(Long barcode);

    @Query("SELECT b FROM BookItem b WHERE b.ISBN = ?1")
    List<BookItem> findByISBN(Long isbn);

    @Query("UPDATE BookItem b SET b.status = ?2 WHERE b.barcode = ?1")
    long updateStatus(Long barcode, BookStatus newStatus);
}
