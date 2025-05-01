package com.conexa.main.Services.impl;

import com.conexa.main.client.IGenericClient;
import com.conexa.main.model.People;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl extends GenericServiceImpl<People, Integer> {
    private final IGenericClient<People> peopleClient;

    public PeopleServiceImpl(IGenericClient<People> peopleClient) {
        this.peopleClient = peopleClient;
    }

    @Override
    protected IGenericClient<People> getFeignClient() {
        return peopleClient;
    }
}
