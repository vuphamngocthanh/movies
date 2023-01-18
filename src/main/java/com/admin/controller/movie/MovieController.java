package com.admin.controller.movie;

import com.admin.service.category.ICategoryService;
import com.admin.service.episode.IEpisodeService;
import com.admin.service.movie.IMovieService;
import com.admin.service.region.IRegionsService;
import com.model.dto.CategoryDto;
import com.model.dto.EpisodeDto;
import com.model.dto.MovieDto;
import com.model.dto.RegionDto;
import com.model.entity.Episode;
import com.model.entity.Movie;
import com.model.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.script.Bindings;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class MovieController {
    private final IEpisodeService episodeService;
    private final IRegionsService regionsService;
    private final ICategoryService categoryService;
    private final IMovieService movieService;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir")
            + "/src/main/resources/uploads";
    @ModelAttribute("categories")
    public Iterable<CategoryDto> categoryDtoList(){
        return categoryService.findAll();
    }
    @ModelAttribute("regions")
    public Iterable<RegionDto> regionDtoList(){
        return regionsService.findAll();
    }
    @ModelAttribute("episodes")
    public Iterable<EpisodeDto> episodeDtoList(){ return episodeService.findAll();}
    public MovieController(IMovieService movieService, IRegionsService regionsService, ICategoryService categoryService, IEpisodeService episodeService) {
        this.movieService = movieService;
        this.regionsService = regionsService;
        this.categoryService = categoryService;
        this.episodeService = episodeService;
    }

    @GetMapping("/movies")
    private ModelAndView showListMovie(){
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("movies",movieService.findAll());
        return modelAndView;
    }
    @GetMapping("/movies/create")
    public ModelAndView createMovie(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("movie",new MovieDto());
        return modelAndView;
    }
    @RequestMapping(value = "/movies/create/save",params = {"add"})
    public ModelAndView addRowEpisode(Movie movie){
        movie.getEpisodes().add(new Episode());
        return new ModelAndView("create");
    }
    @RequestMapping(value = "/movies/create/save",params = {"remove"})
    public ModelAndView removeRowEpisode(Movie movie, HttpServletRequest req){
        Integer rowId = Integer.valueOf(req.getParameter("remove"));
        movie.getEpisodes().remove(rowId.intValue());
        return new ModelAndView("create");
    }
    @PostMapping("/movies/create/save")
    public ModelAndView saveMovie(MovieDto movieDto, @RequestParam("thumb") MultipartFile thumbImg
                            , @RequestParam("poster") MultipartFile posterImg) throws IOException {
        StringBuilder thumb = new StringBuilder();
        StringBuilder poster = new StringBuilder();
        Path thumbImage = Paths.get(UPLOAD_DIRECTORY, thumbImg.getOriginalFilename());
        Path posterImage = Paths.get(UPLOAD_DIRECTORY, posterImg.getOriginalFilename());
        thumb.append(thumbImg.getOriginalFilename());
        poster.append(posterImg.getOriginalFilename());
        Files.write(thumbImage, thumbImg.getBytes());
        Files.write(posterImage, posterImg.getBytes());
        movieDto.setMovieThumb("/uploads/" + thumb.toString());
        movieDto.setMoviePoster("/uploads/" + poster.toString());
        movieService.save(movieDto);
        return new ModelAndView("redirect:/admin/movies");
    }
    @RequestMapping(value = "/movies/update",params = {"add"})
    public ModelAndView editRowEpisode(Movie movie){
        movie.getEpisodes().add(new Episode());
        return new ModelAndView("edit");
    }
    @RequestMapping(value = "/movies/update",params = {"remove"})
    public ModelAndView removeEditRow(Movie movie, HttpServletRequest req){
        Integer rowId = Integer.valueOf(req.getParameter("remove"));
        movie.getEpisodes().remove(rowId.intValue());
        return new ModelAndView("edit");
    }
    @PostMapping("/movies/update")
    public ModelAndView updateMovie(MovieDto movieDto){
        movieService.save(movieDto);
        return new ModelAndView("redirect:/admin/movies");
    }
    @GetMapping("/movies/edit-{id}")
    public ModelAndView editMovies(@PathVariable(value = "id") Integer id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("movie",movieService.findById(id));
        return modelAndView;
    }
    @GetMapping("/movies/delete-{id}")
    private ModelAndView deleteMovie(@PathVariable(value = "id") Integer id) {
        MovieDto movieDto = movieService.findById(id);
//        String pathPoster = ((System.getProperty("user.dir")) + "/src/main/" + movieDto.getMoviePoster()) ;
//        String pathThumb = ((System.getProperty("user.dir")) + "/src/main/" + movieDto.getMovieThumb()) ;
        Path path = Paths.get(movieDto.getMoviePoster());
        try {
            Files.delete(path);
        }
        catch (IOException e) {
            System.out.println("not delete");
        }
        movieService.remove(id);
        return new ModelAndView("redirect:/admin/movies");
    }
}
