package com.iutlibrary.backend.utility;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Represents all constants values used in the application.
 */
public class Constants {
    /**
     * @field MAX_BOOKS_ISSUED_TO_A_USER represents a max number of books that can ve borrowed by a student.
     * @field DEADLINE represents the book return date.
     * @field FINE_FOR_OVERDUE represents a total amount of fine for overdue of returning a book.
     * @field FINE_FOR_LOST represents a total amount of fine for losing a book.
     */
    public static final int MAX_BOOKS_ISSUED_TO_A_USER = 5;
    public static final LocalDate DEADLINE = LocalDate.of(2022, 12, 26);
    public static final Long FINE_FOR_OVERDUE = 50000L;
    public static final Long FINE_FOR_LOST = 50000L;
}
