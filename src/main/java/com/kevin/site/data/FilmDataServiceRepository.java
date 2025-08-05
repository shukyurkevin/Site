package com.kevin.site.data;

import com.kevin.site.dto.GenreFilmsDto;
import com.kevin.site.dto.GenreSeriesDto;
import com.kevin.site.entity.FilmEntity;
import com.kevin.site.models.FilmModel;
import com.kevin.site.interfaces.FilmRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FilmDataServiceRepository {
  final
  FilmRepository filmRepository;

  private final List<String> genres = List.of("Drama", "Action", "Thriller", "Horror", "Comedy", "Sci-Fi", "Fantasy", "Crime");
  ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public FilmDataServiceRepository(FilmRepository filmRepository) {


    this.filmRepository = filmRepository;
  }

  public FilmModel getById(Long id) {

   /*FilmEntity entity = filmRepositoryInterface.findById(id).orElse(null);
    return new FilmModel(entity.getId(), entity.getTitle(),entity.getRating(),entity.getGenres()
    ,entity.getAuthors(),entity.getReleaseYear(),entity.getDurationMinutes(),entity.getDescription()
    , entity.getLanguage(), entity.getType(), entity.getCast(), entity.getCoverImageUrl());
 */
    FilmEntity entity = filmRepository.findById(id).orElse(null);
    return modelMapper.map(entity,FilmModel.class);
  }

  public List<FilmModel> getFilms() {
     Iterable<FilmEntity> filmEntities = filmRepository.findAll();
     List<FilmModel> films = new ArrayList<>();
     filmEntities.forEach(film->films.add(modelMapper.map(film,FilmModel.class)));
     return films;
  }

   public List<FilmModel> searchFilms(String searchTerm) {
    List<FilmEntity> filmEntities = filmRepository.findByTitleContainingIgnoreCase(searchTerm);
    List<FilmModel> films = new ArrayList<>();
    filmEntities.forEach(film->films.add(modelMapper.map(film,FilmModel.class)));

    return films;
  }

  public List<FilmModel> searchByGenres(String searchTerm) {
    List<FilmEntity> filmEntities = filmRepository.findByGenresContainingIgnoreCase(searchTerm);
    List<FilmModel> films = new ArrayList<>();
    filmEntities.forEach(film->films.add(modelMapper.map(film,FilmModel.class)));

    return films;
  }

  public Long addOne(FilmModel newFilm) {
    FilmEntity filmEntity = modelMapper.map(newFilm, FilmEntity.class);
    FilmEntity result = filmRepository.save(filmEntity);
    if (result == null){
      return 0L;
    }else return 1L;
  }


  public boolean deleteOne(Long id) {
    return filmRepository.deleteFilmById(id);
  }


  public FilmModel updateOne(Long id, FilmModel filmModel) {
    FilmEntity entity = modelMapper.map(filmModel, FilmEntity.class);
    FilmEntity result = filmRepository.save(entity);
    return modelMapper.map(result,FilmModel.class);
  }

  public List<FilmModel> getLatestFilms() {
    List<FilmEntity> filmEntities = filmRepository.findAllByOrderByIdDesc();
    List<FilmModel> films = new ArrayList<>();
    filmEntities.forEach(film->films.add(modelMapper.map(film,FilmModel.class)));
    return films;
  }

  public List<FilmModel> getLatestOnlyFilms(){
    List<FilmEntity> filmEntities = filmRepository.findAllByOrderByIdDesc();
    List<FilmModel> films = new ArrayList<>();
    for (int i = 0;i<=filmEntities.size()-1 ;i++){
      if (filmEntities.get(i).getType().equalsIgnoreCase("Movies") ){
        films.add(modelMapper.map(filmEntities.get(i),FilmModel.class));
      }
    }
    return films;
  }


  public List<FilmModel> getLatestOnlySeries(){
    List<FilmEntity> filmEntities = filmRepository.findAllByOrderByIdDesc();
    List<FilmModel> films = new ArrayList<>();
    for (int i = 0;i<=filmEntities.size()-1 ;i++){
      if (filmEntities.get(i).getType().equalsIgnoreCase("Series") ){
        films.add(modelMapper.map(filmEntities.get(i),FilmModel.class));
      }
    }
    return films;
  }


  public List<FilmModel> getSeries(){
    List<FilmEntity> filmEntities = filmRepository.findByTypeContainingIgnoreCase("Series");
    List<FilmModel> series = new ArrayList<>();
    filmEntities.forEach(film->series.add(modelMapper.map(film,FilmModel.class)));
    return series;
  }


  public List<FilmModel> getOnlyFilms(){
    List<FilmEntity> filmEntities = filmRepository.findByTypeContainingIgnoreCase("Movies");
    List<FilmModel> series = new ArrayList<>();
    filmEntities.forEach(film->series.add(modelMapper.map(film,FilmModel.class)));
    return series;
  }


  public List<GenreFilmsDto> getFilmsByGenres() {
    List<GenreFilmsDto> result = new ArrayList<>();

    for (String genre : genres){
      List<FilmEntity> films = filmRepository.findByTypeContainingIgnoreCaseAndGenresContainingIgnoreCase("Movies",genre);
      result.add(new GenreFilmsDto(genre,films));
    }

    return result;
  }


  public List<GenreSeriesDto> getSeriesByGenres() {
    List<GenreSeriesDto> result = new ArrayList<>();

    for (String genre : genres){
      List<FilmEntity> films = filmRepository.findByTypeContainingIgnoreCaseAndGenresContainingIgnoreCase("Series",genre);
      result.add(new GenreSeriesDto(genre,films));
    }

    return result;
  }


  public List<FilmModel> getHighestRated() {
    List<FilmEntity> allFilms = (List<FilmEntity>) filmRepository.findAll();
    List<FilmEntity> sortedList = allFilms.stream()
        .sorted(Comparator.comparing(FilmEntity::getRating).reversed())
        .toList();
    List<FilmModel> sortedFilms = new ArrayList<>();
    sortedList.forEach(film->sortedFilms.add(modelMapper.map(film,FilmModel.class)));
    return sortedFilms;
  }


  public List<FilmModel> getHighestRatedMovies() {
    List<FilmEntity> allFilms = (List<FilmEntity>) filmRepository.findByTypeContainingIgnoreCase("Movies");
    List<FilmEntity> sortedList = allFilms.stream()
        .sorted(Comparator.comparing(FilmEntity::getRating).reversed())
        .toList();
    List<FilmModel> sortedFilms = new ArrayList<>();
    sortedList.forEach(film->sortedFilms.add(modelMapper.map(film,FilmModel.class)));
    return sortedFilms;
  }

  public List<FilmModel> getHighestRatedSeries() {
    List<FilmEntity> allFilms = (List<FilmEntity>) filmRepository.findByTypeContainingIgnoreCase("Series");
    List<FilmEntity> sortedList = allFilms.stream()
        .sorted(Comparator.comparing(FilmEntity::getRating).reversed())
        .toList();
    List<FilmModel> sortedFilms = new ArrayList<>();
    sortedList.forEach(film->sortedFilms.add(modelMapper.map(film,FilmModel.class)));
    return sortedFilms;
  }
}

