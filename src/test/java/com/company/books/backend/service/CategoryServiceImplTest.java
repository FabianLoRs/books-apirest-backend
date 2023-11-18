package com.company.books.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.books.backend.models.Category;
import com.company.books.backend.models.dao.ICategoryDao;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.services.CategoryServiceImpl;

public class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl service;

    @Mock
    ICategoryDao categoryDao;

    List<Category> listCategories = new ArrayList<Category>();
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.loadCategories();
    }

    @Test
    public void findCategoriesTest() {
        when(categoryDao.findAll()).thenReturn(listCategories);

        ResponseEntity<CategoryResponseRest> response = service.findCategories();

        // var responseNotNull = response.getBody() ? response.getBody() : null;

        assertEquals(4, response.getBody() .getCategoryResponse().getCategory().size());

        verify(categoryDao, times(1)).findAll();
    }

    public void loadCategories() {
        Category catOne = new Category(Long.valueOf(1), "Beer", "A lot of beers ");
        Category catTwo = new Category(Long.valueOf(1), "Apples", "A lot of apples");
        Category catThree = new Category(Long.valueOf(1), "Bread", "A few of bread");
        Category catFour = new Category(Long.valueOf(1), "Drink", "Sugar free drink");

        listCategories.add(catOne);
        listCategories.add(catTwo);
        listCategories.add(catThree);
        listCategories.add(catFour);
    }
}
