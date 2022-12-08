package com.iutlibrary.backend.utility;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a basic info object.
 * This type of request is required from a client for a server so that server can send as a response
 * each studet's id and total number of books they currently borrow.
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentBasicInfo {
    /**
     * @field studentId a string value representing a student's id.
     * @field noOfInUseBooks a integer value representing a number of all currently borrowed books by a student.
     */
    private final String studentId;
    private final Integer noOfInUseBooks;
}
