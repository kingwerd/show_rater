package com.edward.beltexam.repositories;

import java.util.List;

import com.edward.beltexam.models.Show;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends CrudRepository<Show, Long> {

    List<Show> findAll();

    Show findByTitle(String search);
    
    @Query(value="SELECT * from shows ORDER BY averageRating desc", nativeQuery=true)
	List<Show> findByTitleByAvgDesc();

} 