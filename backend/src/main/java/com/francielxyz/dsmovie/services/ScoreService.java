package com.francielxyz.dsmovie.services;

import com.francielxyz.dsmovie.dto.MovieDTO;
import com.francielxyz.dsmovie.dto.ScoreDTO;
import com.francielxyz.dsmovie.entities.Movie;
import com.francielxyz.dsmovie.entities.Score;
import com.francielxyz.dsmovie.entities.User;
import com.francielxyz.dsmovie.repository.MovieRepository;
import com.francielxyz.dsmovie.repository.ScoreRepository;
import com.francielxyz.dsmovie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO scoreDTO) {
        User user = userRepository.findByEmail(scoreDTO.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(scoreDTO.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        Movie movie = movieRepository.findById(scoreDTO.getMovieId()).get();

        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(scoreDTO.getScore());
        scoreRepository.saveAndFlush(score);

        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        movie.getScores().forEach(value -> {
            sum.updateAndGet(v -> v + value.getValue());

        });

        double avg = sum.get() / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());

        return new MovieDTO(movieRepository.saveAndFlush(movie));
    }
}
