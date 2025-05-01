package com.conexa.main.Services;

import com.conexa.main.model.CustomPage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IStarWarsService<T, ID> {
    CustomPage<T> getAll(Pageable pageable);
}
