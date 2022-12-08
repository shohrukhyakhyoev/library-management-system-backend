package com.iutlibrary.backend.bookStuff.bookItem;

import com.iutlibrary.backend.utility.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;


/**
 * Serves as a data layer for manipulation over data of book item.
 * It allows application to contact with database with the help of
 * functions together with the specified SQL Query. To accomplish it
 * interface extends JpaRepository class. By this we use Spring Data
 * JPA Framework to map objects in database table. It is called ORM.
 *
 * @author shohrukhyakhyoev
 */
public interface BookItemRepository extends JpaRepository<BookItem, Long> {

    /**
     * Gets details of all book items of a particular book that match to a given status.

     * @param ISBN represents book's isbn.
     * @param bookStatus represents book item's status.
     * @return
     */
    @Query("SELECT b FROM BookItem b WHERE b.ISBN = ?1 AND b.status = ?2")
    List<BookItem> findTopByISBNAndStatus(Long ISBN, BookStatus bookStatus);

    /**
     * Gets details of all book items that match to a given status.
     *
     * @param status represents a book item's status.
     * @return list of BookItem objects.
     */
    @Query("SELECT b From BookItem b WHERE lower(b.status) = lower(?1)")
    List<BookItem> findBookItemByStatus(String status);

    /**
     * Gets details of a book item with a given barcode.
     *
     * @param barcode represents a book item's barcode.
     * @return BookItem object.
     */
    @Query("SELECT b From BookItem b WHERE b.barcode = ?1")
    Optional<BookItem> findByBarcode(Long barcode);

    /**
     * Gets details of all book items of a particular isbn.
     *
     * @param isbn represents a book's isbn.
     * @return list of BookItem objects.
     */
    @Query("SELECT b FROM BookItem b WHERE b.ISBN = ?1")
    List<BookItem> findByISBN(Long isbn);

    /**
     * Updates status of a book item.
     *
     * @param barcode represents a book item's barcode.
     * @param newStatus represents a new status value of a book item.
     * @return number of rows affected afer an execution of query.
     */
    @Query("UPDATE BookItem b SET b.status = ?2 WHERE b.barcode = ?1")
    long updateStatus(Long barcode, BookStatus newStatus);
}
