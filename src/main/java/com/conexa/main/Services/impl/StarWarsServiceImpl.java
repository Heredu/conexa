package com.conexa.main.Services.impl;

import com.conexa.main.Services.IStarWarsService;
import com.conexa.main.client.IStarWarsClient;
import com.conexa.main.model.CustomPage;
import com.conexa.main.model.SWApiResponse;
import com.conexa.main.model.SWApiUnitResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StarWarsServiceImpl<T> implements IStarWarsService<T> {

    private final IStarWarsClient starWarsClient;

    public StarWarsServiceImpl(IStarWarsClient starWarsClient) {
        this.starWarsClient = starWarsClient;
    }

    @Override
    public CustomPage<T> getAll(Pageable pageable, String resource, Class<T> type) {
        SWApiResponse<?> swApiResponse = starWarsClient.getAll(resource, pageable.getPageNumber(), pageable.getPageSize());
        return CustomPage.fromSwapiResponse(convertResponse(swApiResponse), pageable);
    }

    @Override
    public SWApiUnitResponse<T> getById(int id, String resource, Class<T> resourceType) {
        SWApiUnitResponse<?> swApiUnitResponse = starWarsClient.getResourceById(resource, id);
        return (convertUnitResponse(swApiUnitResponse));
    }

    @SuppressWarnings("unchecked")
    private <U> SWApiResponse<U> convertResponse(SWApiResponse<?> response) {
        return (SWApiResponse<U>) response;
    }

    @SuppressWarnings("unchecked")
    private <U> SWApiUnitResponse<U> convertUnitResponse(SWApiUnitResponse<?> response) {
        return (SWApiUnitResponse<U>) response;
    }
}


