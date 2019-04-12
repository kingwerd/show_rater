package com.edward.beltexam.repositories;

import java.util.List;

import com.edward.beltexam.models.Rating;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {

    List<Rating> findAll();

    @Query(value="SELECT * FROM ratings WHERE show_id = ?1 ORDER BY rate ASC", nativeQuery=true)
    List<Rating> findRatingsByShowByAverageRatingAsc(long id);
} 