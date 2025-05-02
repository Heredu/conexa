package com.conexa.main.model;

import lombok.Getter;

@Getter
public class SWApiUnitResponse<T> {
    private SWResult<T> result;
}
