package com.conexa.main.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

import java.util.List;
@Getter
public class SWApiUnitListResponse<T> {
    @JsonAlias({"result", "results"})
    private List<SWResult<T>> result;

}
