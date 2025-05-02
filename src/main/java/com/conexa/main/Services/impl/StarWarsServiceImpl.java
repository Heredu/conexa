package com.conexa.main.Services.impl;

import com.conexa.main.Services.IStarWarsService;
import com.conexa.main.client.IStarWarsClient;
import com.conexa.main.model.CustomPage;
import com.conexa.main.model.SWApiResponse;
import com.conexa.main.model.SWApiUnitListResponse;
import com.conexa.main.model.SWApiUnitResponse;
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
        SWApiResponse<?> swApiResponse = starWarsClient.getAll(resource, pageable.getPageNumber(), pageable.getPageSize());
        return CustomPage.fromSwapiResponse(convertResponse(swApiResponse), pageable);
    }

    @Override
    public SWApiUnitResponse<T> getById(int id, String resource) {
        SWApiUnitResponse<?> swApiUnitResponse = starWarsClient.getResourceById(resource, id);
        return (convertUnitResponse(swApiUnitResponse));
    }

    @Override
    public Page<SWResult<T>> search(String name, String title, int page, int size, String resource) {
        SWApiUnitListResponse<?> swApiUnitResponse = starWarsClient.getSearch(resource, name, title);
        if (swApiUnitResponse == null || swApiUnitResponse.getResult() == null) {
            return new PageImpl<>(Collections.emptyList());
        }
        SWApiUnitListResponse<T> swApiUnitListResponse = (convertUnitListResponse(swApiUnitResponse));
        List<SWResult<T>> result = swApiUnitListResponse.getResult();
        int totalElements = result.size();
        page = Math.max(0, page);
        size = Math.min(size, Math.max(10, totalElements));
        int fromIndex = Math.min(page * size, totalElements);
        int toIndex = Math.min(fromIndex + size, totalElements);
        List<SWResult<T>> pageContent = result.subList(fromIndex, toIndex);
        return new PageImpl<>(pageContent, PageRequest.of(page, size), totalElements);
    }

    @SuppressWarnings("unchecked")
    private <U> SWApiResponse<U> convertResponse(SWApiResponse<?> response) {
        return (SWApiResponse<U>) response;
    }

    @SuppressWarnings("unchecked")
    private <U> SWApiUnitResponse<U> convertUnitResponse(SWApiUnitResponse<?> response) {
        return (SWApiUnitResponse<U>) response;
    }
    @SuppressWarnings("unchecked")
    private <U> SWApiUnitListResponse<U> convertUnitListResponse(SWApiUnitListResponse<?> response) {
        return (SWApiUnitListResponse<U>) response;
    }
}


