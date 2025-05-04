package com.conexa.main.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SWApiUnitResponse<T> {
    private SWResult<T> result;
}
