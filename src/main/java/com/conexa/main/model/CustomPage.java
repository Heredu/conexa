package com.conexa.main.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
public class CustomPage<T> {

    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int number;
    private int size;
    private boolean first;
    private boolean last;

    public static <T> CustomPage<T> fromSwapiResponse(SWApiResponse<T> SWApiResponse, Pageable pageable) {
        CustomPage<T> page = new CustomPage<>();
        page.setContent((List<T>) SWApiResponse.getResults());
        page.setTotalPages(SWApiResponse.getTotalPages());
        page.setTotalElements(SWApiResponse.getTotalRecords());
        page.setNumber(pageable.getPageNumber());
        page.setSize(pageable.getPageSize());
        page.setFirst(SWApiResponse.getPrevious() == null);
        page.setLast(SWApiResponse.getNext() == null);
        return page;
    }
}
