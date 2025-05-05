package com.conexa.main.Services;

import com.conexa.main.model.CustomPage;
import com.conexa.main.model.StarWarsApiResponseGetById;
import com.conexa.main.model.SWResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IStarWarsService<T> {
    CustomPage<T> getAll(Pageable pageable, String resource);

    StarWarsApiResponseGetById<T> getById(int id, String resourceName);

    Page<SWResult<T>> search(String name, String title, int page, int size, String resource);
}
