package com.conexa.main.model;

import lombok.Getter;

import java.util.List;
@Getter
public class SWApiUnitListResponse<T> {
    private List<SWResult<T>> result;

}
