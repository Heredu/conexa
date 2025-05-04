package com.conexa.main.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SWApiResponse<T> {
    private String message;

    @JsonProperty("total_records")
    private int totalRecords;

    @JsonProperty("total_pages")
    private int totalPages;

    private String previous;
    private String next;
    @JsonAlias({"result", "results"})
    private List<T> results;

    @JsonProperty("apiVersion")
    private String apiVersion;

    private Date timestamp;
}
