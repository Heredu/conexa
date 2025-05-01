package com.conexa.main.Services.impl;

import com.conexa.main.Services.IStarWarsService;
import com.conexa.main.client.IGenericClient;
import com.conexa.main.model.CustomPage;
import com.conexa.main.model.SWApiResponse;
import com.conexa.main.model.StarWarsResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImpl<T extends StarWarsResource, ID> implements IStarWarsService<T, ID> {

    protected abstract IGenericClient<T> getFeignClient();

    @Override
    public CustomPage<T> getAll(Pageable pageable) {
        SWApiResponse<T> SWApiResponse = getFeignClient().getAll(pageable.getPageNumber(),pageable.getPageSize());
        return CustomPage.fromSwapiResponse(SWApiResponse, pageable);
    }
}


