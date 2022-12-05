package com.iutlibrary.backend.utility.enums;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentBasicInfo {
    private final String studentId;
    private final Integer noOfInUseBooks;
}
