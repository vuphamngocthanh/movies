package com.admin.service.movie;

import com.model.dto.MovieDto;
import com.model.entity.Episode;
import com.model.entity.Movie;
import com.repo.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService{
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    public MovieService(ModelMapper modelMapper, MovieRepository movieRepository) {
        this.modelMapper = modelMapper;
        this.movieRepository = movieRepository;
    }

    @Override
    public Iterable<MovieDto> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.parallelStream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public MovieDto findById(Integer id) {
        Movie movie = movieRepository.findById(id).get();
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public void save(MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        movie.setEpisodes(movie.getEpisodes());
        movieRepository.saveAndFlush(movie);
    }
    @Override
    public void remove(Integer id) {
        movieRepository.deleteById(id);
    }
}
