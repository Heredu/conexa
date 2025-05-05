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

    public static <T> CustomPage<T> fromSwapiResponse(StarWarsApiResponseGetAll<T> StarWarsApiResponseGetAll, Pageable pageable) {
        CustomPage<T> page = new CustomPage<>();
        page.setContent((List<T>) StarWarsApiResponseGetAll.getResults());
        page.setTotalPages(StarWarsApiResponseGetAll.getTotalPages());
        page.setTotalElements(StarWarsApiResponseGetAll.getTotalRecords());
        page.setNumber(pageable.getPageNumber());
        page.setSize(pageable.getPageSize());
        page.setFirst(StarWarsApiResponseGetAll.getPrevious() == null);
        page.setLast(StarWarsApiResponseGetAll.getNext() == null);
        return page;
    }
}
