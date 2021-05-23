package com.gadek.restapi.dto;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Builder
@Data
//@NoArgsConstructor
public class PageDTO {
    private int pageNumber;
    private int pageSize;
    long totalRecord;
    int totalPages;
//    long offset
}
