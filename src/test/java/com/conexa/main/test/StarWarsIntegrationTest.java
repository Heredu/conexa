package com.conexa.main.test;

import com.conexa.main.Services.impl.StarWarsServiceImpl;
import com.conexa.main.controllers.PeopleController;
import com.conexa.main.model.CustomPage;
import com.conexa.main.model.People;
import com.conexa.main.model.SWApiUnitResponse;
import com.conexa.main.model.SWResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StarWarsIntegrationTest {

    @InjectMocks
    private PeopleController peopleController;

    @Mock
    private StarWarsServiceImpl<People> starWarsService;

    @Test
    void getAllPeople_shouldReturnPaginatedResults() {
        // arrange
        CustomPage<People> mockPage = new CustomPage<>();
        mockPage.setContent(Collections.singletonList(new People()));
        when(starWarsService.getAll(any(Pageable.class), anyString()))
                .thenReturn(mockPage);

        // act
        ResponseEntity<CustomPage<People>> response = peopleController.getAll(0, 10);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    void getPeopleById_shouldReturnPerson() {
        // arrange
        SWApiUnitResponse<People> mockResponse = new SWApiUnitResponse<>();
        mockResponse.setResult(new SWResult<>());
        when(starWarsService.getById(anyInt(), anyString()))
                .thenReturn(mockResponse);

        // act
        ResponseEntity<SWApiUnitResponse<People>> response = peopleController.getById(1);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getResult());
    }

}
