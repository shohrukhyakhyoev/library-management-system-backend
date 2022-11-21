package com.iutlibrary.backend.utility;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Constants {
    public static final int MAX_BOOKS_ISSUED_TO_A_USER = 5;
    public static final LocalDate DEADLINE = LocalDate.of(2022, 12, 26);
    public static final Long FINE_FOR_OVERDUE = 50000L;
    public static final Long FINE_FOR_LOST = 50000L;
}
