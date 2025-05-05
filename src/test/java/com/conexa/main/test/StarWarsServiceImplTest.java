package com.conexa.main.test;

import com.conexa.main.Services.impl.StarWarsServiceImpl;
import com.conexa.main.client.IStarWarsClient;
import com.conexa.main.model.CustomPage;
import com.conexa.main.model.People;
import com.conexa.main.model.StarWarsApiResponseGetAll;
import com.conexa.main.model.StarWarsApiResponseGetSearch;
import com.conexa.main.model.StarWarsApiResponseGetById;
import com.conexa.main.model.SWResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StarWarsServiceImplTest {

    private static final String RESOURCE = "people";
    private static final Pageable PAGEABLE = PageRequest.of(0, 10);
    private static final int TEST_ID = 1;

    @Mock
    private IStarWarsClient starWarsClient;

    @InjectMocks
    private StarWarsServiceImpl<People> starWarsService;

    private People testPeople;
    private SWResult<People> testResult;

    @BeforeEach
    void setUp() {
        testPeople = new People();
        testPeople.setName("Luke Skywalker");

        testResult = new SWResult<>();
        testResult.setProperties(testPeople);
    }

    @Test
    void getAll_ShouldReturnCustomPage_WhenClientReturnsResponse() {
        // Arrange
        StarWarsApiResponseGetAll<People> mockResponse = new StarWarsApiResponseGetAll<>();
        mockResponse.setResults(Collections.singletonList(testPeople));

        when(starWarsClient.getAll(anyString(), anyInt(), anyInt()))
                .thenAnswer(invocation -> mockResponse);

        // Act
        CustomPage<People> result = starWarsService.getAll(PAGEABLE, RESOURCE);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Luke Skywalker", result.getContent().get(0).getName());
        verify(starWarsClient).getAll(eq(RESOURCE), eq(0), eq(10));
    }

    @Test
    void getById_ShouldReturnUnitResponse_WhenClientReturnsResponse() {
        // Arrange
        StarWarsApiResponseGetById<People> mockResponse = new StarWarsApiResponseGetById<>();
        mockResponse.setResult(testResult);

        when(starWarsClient.getResourceById(anyString(), anyInt()))
                .thenAnswer(invocation -> mockResponse);

        // Act
        StarWarsApiResponseGetById<People> result = starWarsService.getById(TEST_ID, RESOURCE);

        // Assert
        assertNotNull(result);
        assertEquals("Luke Skywalker", result.getResult().getProperties().getName());
        verify(starWarsClient).getResourceById(eq(RESOURCE), eq(TEST_ID));
    }

    @Test
    void getById_ShouldReturnNull_WhenClientReturnsNull() {
        // Arrange
        when(starWarsClient.getResourceById(anyString(), anyInt()))
                .thenAnswer(invocation -> null);

        // Act
        StarWarsApiResponseGetById<People> result = starWarsService.getById(TEST_ID, RESOURCE);

        // Assert
        assertNull(result);
    }

    @Test
    void search_ShouldReturnPage_WhenResultsFound() {
        // Arrange
        StarWarsApiResponseGetSearch<People> mockResponse = new StarWarsApiResponseGetSearch<>();
        mockResponse.setResult(Arrays.asList(testResult, testResult));

        doReturn(mockResponse)
                .when(starWarsClient)
                .getSearch(eq(RESOURCE), eq("Luke"), isNull());
        // Act
        Page<SWResult<People>> result = starWarsService.search(
                "Luke", null, 0, 1, RESOURCE);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals("Luke Skywalker", result.getContent().get(0).getProperties().getName());
    }

    @Test
    void search_ShouldReturnEmptyPage_WhenNoResults() {
        // Arrange
        doReturn(null)
                .when(starWarsClient)
                .getSearch(eq(RESOURCE), eq("Luke"), isNull());

        // Act
        Page<SWResult<People>> result = starWarsService.search(
                "Luke", null, 0, 10, RESOURCE);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void search_ShouldHandlePaginationCorrectly() {
        // Arrange
        StarWarsApiResponseGetSearch<People> mockResponse = new StarWarsApiResponseGetSearch<>();
        List<SWResult<People>> results = Arrays.asList(
                testResult,
                testResult,
                testResult
        );
        mockResponse.setResult(results);

        doReturn(mockResponse)
                .when(starWarsClient)
                .getSearch(eq(RESOURCE), eq("name"), isNull());

        // Act - Page 0, size 2
        Page<SWResult<People>> page1 = starWarsService.search(
                "name", null, 0, 2, RESOURCE);

        // Act - Page 1, size 2
        Page<SWResult<People>> page2 = starWarsService.search(
                "name", null, 1, 2, RESOURCE);

        // Assert
        assertEquals(3, page1.getTotalElements());
        assertEquals(2, page1.getContent().size());
        assertEquals(1, page2.getContent().size());
    }
}