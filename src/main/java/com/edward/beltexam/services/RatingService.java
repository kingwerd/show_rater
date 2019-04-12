package com.edward.beltexam.services;

import java.util.List;
import java.util.Optional;

import com.edward.beltexam.models.Rating;
import com.edward.beltexam.models.Show;
import com.edward.beltexam.models.User;
import com.edward.beltexam.repositories.RatingRepository;

import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> allRatings() {
        return ratingRepository.findAll();
    }

    public List<Rating> ascendingRatings(long id) {
        return ratingRepository.findRatingsByShowByAverageRatingAsc(id);
    }

    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating findRating(Long id) {
        Optional<Rating> optionalRating = ratingRepository.findById(id);
        if (optionalRating.isPresent()) {
            return optionalRating.get();
        } else {
            return null;
        }
    }

    public void addRatingToShow(Rating rating, Show show) {
        rating.setShow(show);
        ratingRepository.save(rating);
    }

    public void addRatingToUser(Rating rating, User user) {
        rating.setUser(user);
        ratingRepository.save(rating);
    }
}