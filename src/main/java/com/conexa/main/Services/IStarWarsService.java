package com.conexa.main.Services;

import com.conexa.main.model.CustomPage;
import com.conexa.main.model.SWApiUnitResponse;
import com.conexa.main.model.SWResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IStarWarsService<T> {
    CustomPage<T> getAll(Pageable pageable, String resource, Class<T> type);

    SWApiUnitResponse<T> getById(int id, String resourceName, Class<T> resourceType);

    Page<SWResult<T>> search(String name, String title, int page, int size, String resource, Class<T> resourceType);
}
