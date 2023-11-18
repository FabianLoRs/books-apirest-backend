package com.company.books.backend.services;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.models.Category;
import com.company.books.backend.models.dao.ICategoryDao;
import com.company.books.backend.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> findCategories() {

        log.info("Start findCategories method");
        CategoryResponseRest response = new CategoryResponseRest();

        try {
            List<Category> category = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategory(category);
            response.setMetadata("OK", "00", "Successful Response.");
        } catch (Exception ex) {

            log.error("Error while querying categories: ", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Fail", "-11", "Error while querying categories.");
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> findById(Long id) {

        log.info("Start findById() method.");
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> listCategories = new ArrayList<>();

        try {

            Optional<Category> category = categoryDao.findById(id);

            if (category.isPresent()) {
                listCategories.add(category.get());
                response.getCategoryResponse().setCategory(listCategories);

            } else {
                log.error("Error to get Category.");
                response.setMetadata("Fail", "-11", "Category not found.");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);

            }
        } catch (Exception ex) {
            log.error("Error to get Category.", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Fail", "-11", "Error to get category.");
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.setMetadata("OK", "00", "Successful Response.");
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> createCategory(Category category) {

        log.info("Start createCategory() method.");
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> listCategories = new ArrayList<>();

        try {

            Category saveCategory = categoryDao.save(category);
            if (saveCategory != null) {
                listCategories.add(saveCategory);
                response.getCategoryResponse().setCategory(listCategories);

            } else {
                log.error("Error to save Category.");
                response.setMetadata("Fail", "-11", "Category not saved.");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);

            }
        } catch (Exception ex) {
            log.error("Error to create category.", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Fail", "-11", "Error to create category.");
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        
        response.setMetadata("OK", "00", "Successful Response.");
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id) {

        log.info("Start uptadeCategory() method.");
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> listCategories = new ArrayList<>();

        try {
            Optional<Category> searchedCategory = categoryDao.findById(id);

            if (searchedCategory.isPresent()) {
                searchedCategory.get().setName(category.getName());
                searchedCategory.get().setDescription(category.getDescription());

                Category updateCategory = categoryDao.save(searchedCategory.get());

                if (updateCategory != null) {
                    response.setMetadata("OK", "00", "Successful updated category.");
                    listCategories.add(updateCategory);
                    response.getCategoryResponse().setCategory(listCategories);

                } else {
                    log.error("Error to update category");
                    response.setMetadata("Fail", "-11", "Error to update category.");
                    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                log.error("Error to update category");
                response.setMetadata("Fail", "-11", "Error to update category.");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            log.error("Error to update category", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Fail", "-11", "Error to update category.");
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteCategory(Long id) {
        log.info("Start deleteCategory() method.");
        CategoryResponseRest response = new CategoryResponseRest();

        try {
            categoryDao.deleteById(id);
            response.setMetadata("OK", "00", "Successful deleted category.");
        } catch (Exception ex) {
            log.error("Error to delete category", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Fail", "-11", "Error to delete category.");
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

}
