package com.conexa.main.Services.impl;

import com.conexa.main.Services.IStarWarsService;
import com.conexa.main.client.IStarWarsClient;
import com.conexa.main.model.CustomPage;
import com.conexa.main.model.StarWarsApiResponseGetAll;
import com.conexa.main.model.StarWarsApiResponseGetSearch;
import com.conexa.main.model.StarWarsApiResponseGetById;
import com.conexa.main.model.SWResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StarWarsServiceImpl<T> implements IStarWarsService<T> {

    private final IStarWarsClient starWarsClient;

    public StarWarsServiceImpl(IStarWarsClient starWarsClient) {
        this.starWarsClient = starWarsClient;
    }

    @Override
    public CustomPage<T> getAll(Pageable pageable, String resource) {
        StarWarsApiResponseGetAll<?> starWarsApiResponseGetAll = starWarsClient.getAll(resource, pageable.getPageNumber(), pageable.getPageSize());
        return CustomPage.fromSwapiResponse(convertResponse(starWarsApiResponseGetAll), pageable);
    }

    @Override
    public StarWarsApiResponseGetById<T> getById(int id, String resource) {
        StarWarsApiResponseGetById<?> starWarsApiResponseGetById = starWarsClient.getResourceById(resource, id);
        return (convertUnitResponse(starWarsApiResponseGetById));
    }

    @Override
    public Page<SWResult<T>> search(String name, String title, int page, int size, String resource) {
        StarWarsApiResponseGetSearch<?> swApiUnitResponse = starWarsClient.getSearch(resource, name, title);
        if (swApiUnitResponse == null || swApiUnitResponse.getResult() == null) {
            return new PageImpl<>(Collections.emptyList());
        }
        StarWarsApiResponseGetSearch<T> starWarsApiResponseGetSearch = (convertUnitListResponse(swApiUnitResponse));
        List<SWResult<T>> result = starWarsApiResponseGetSearch.getResult();
        int totalElements = result.size();
        page = Math.max(0, page);
        size = Math.min(size, Math.max(10, totalElements));
        int fromIndex = Math.min(page * size, totalElements);
        int toIndex = Math.min(fromIndex + size, totalElements);
        List<SWResult<T>> pageContent = result.subList(fromIndex, toIndex);
        return new PageImpl<>(pageContent, PageRequest.of(page, size), totalElements);
    }

    @SuppressWarnings("unchecked")
    private <U> StarWarsApiResponseGetAll<U> convertResponse(StarWarsApiResponseGetAll<?> response) {
        return (StarWarsApiResponseGetAll<U>) response;
    }

    @SuppressWarnings("unchecked")
    private <U> StarWarsApiResponseGetById<U> convertUnitResponse(StarWarsApiResponseGetById<?> response) {
        return (StarWarsApiResponseGetById<U>) response;
    }
    @SuppressWarnings("unchecked")
    private <U> StarWarsApiResponseGetSearch<U> convertUnitListResponse(StarWarsApiResponseGetSearch<?> response) {
        return (StarWarsApiResponseGetSearch<U>) response;
    }
}


