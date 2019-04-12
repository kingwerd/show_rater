package com.edward.beltexam.services;

import java.util.List;
import java.util.Optional;

import com.edward.beltexam.models.Rating;
import com.edward.beltexam.models.Show;
import com.edward.beltexam.repositories.ShowRepository;

import org.springframework.stereotype.Service;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> allShows() {
        return showRepository.findAll();
    }

    public List<Show> allAverages() {
        return showRepository.findByTitleByAvgDesc();
    }

    public Show create(Show show) {
        return showRepository.save(show);
    }

    public Show findShow(long id) {
        Optional<Show> optionalShow = showRepository.findById(id);
        if (optionalShow.isPresent()) {
            return optionalShow.get();
        } else {
            return null;
        }
    }

    public Show findByTitle(String title) {
        return showRepository.findByTitle(title);
    }

    public Show update(Show show) {
        return showRepository.save(show);
    }

    public Show updateShow(Long id, String title, String network) {
        Optional<Show> optionalShow = showRepository.findById(id);
        if (optionalShow.isPresent()) {
            Show show = optionalShow.get();
            show.setTitle(title);
            show.setNetwork(network);
            return showRepository.save(show);
        } else {
            return null;
        }
    }

    public Show updateShowAverage(Show show, List<Rating> ratings) {
        double sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getRate();
        }
        double avg = sum/ratings.size();
        show.setAverageRating(avg);
        return showRepository.save(show);
    }

    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }

}