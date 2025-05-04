package com.compurangers.platform.dao;

/**
 * Interface defining the standard CRUD test methods for DAO classes.
 * Implementing classes should provide test logic for each method using JUnit or similar testing frameworks.
 */
public interface ICrudDAOTest {

    /**
     * Tests the insertion of a new record.
     */
    void shouldInsert();

    /**
     * Tests updating a record when the ID exists.
     */
    void shouldUpdateIfIdExists();

    /**
     * Tests that updating a record fails or does nothing when the ID does not exist.
     */
    void shouldNotUpdateIfIdDoesNotExist();

    /**
     * Tests that deleting a record fails or does nothing when the ID does not exist.
     */
    void shouldNotDeleteIfIdDoesNotExist();

    /**
     * Tests finding a record by ID when it exists.
     */
    void shouldFindIfIdExists();

    /**
     * Tests that finding a record by ID returns null or indicates not found when the ID does not exist.
     */
    void shouldNotFindIfIdDoesNotExist();

    /**
     * Tests listing all records.
     */
    void shouldList();

    /**
     * Tests deleting a record when the ID exists.
     */
    void shouldDeleteIfIdExists();
}