package com.iutlibrary.backend.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Serves as a data layer for manipulation over data of image.
 * It allows application to contact with database with the help of
 * functions together with the specified SQL Query. To accomplish it
 * interface extends JpaRepository class. By this we use Spring Data
 * JPA Framework to map objects in database table. It is called ORM.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

}
