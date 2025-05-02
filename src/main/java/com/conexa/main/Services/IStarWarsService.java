package com.conexa.main.Services;

import com.conexa.main.model.CustomPage;
import com.conexa.main.model.SWApiUnitResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IStarWarsService<T> {
    CustomPage<T> getAll(Pageable pageable, String resource, Class<T> type);

    SWApiUnitResponse<T> getById(int id, String resourceName, Class<T> resourceType);
}
