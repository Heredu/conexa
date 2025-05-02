package com.conexa.main.Services.impl;

import com.conexa.main.Services.IStarWarsService;
import com.conexa.main.client.IStarWarsClient;
import com.conexa.main.model.CustomPage;
import com.conexa.main.model.SWApiResponse;
import com.conexa.main.model.StarWarsResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StarWarsServiceImpl<T extends StarWarsResource, ID> implements IStarWarsService<T, ID> {

    private final IStarWarsClient genericClient;

    public StarWarsServiceImpl(IStarWarsClient genericClient) {
        this.genericClient = genericClient;
    }

    @Override
    public CustomPage<T> getAll(Pageable pageable, String resource, Class<T> type) {
        SWApiResponse<?> SWApiResponse = genericClient.getAll(resource, pageable.getPageNumber(), pageable.getPageSize());
        return CustomPage.fromSwapiResponse(convertResponse(SWApiResponse, type), pageable);
    }

    @SuppressWarnings("unchecked")
    private <T> SWApiResponse<T> convertResponse(SWApiResponse<?> response, Class<T> type) {
        return (SWApiResponse<T>) response;
    }
}


