package com.kevin.site.data;

import com.kevin.site.dto.GenreFilmsDto;
import com.kevin.site.dto.GenreSeriesDto;
import com.kevin.site.entity.FilmEntity;
import com.kevin.site.models.FilmModel;
import com.kevin.site.interfaces.FilmDataAccessInterface;
import com.kevin.site.interfaces.FilmRepositoryInterface;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FilmDataServiceRepository implements FilmDataAccessInterface<FilmModel> {
  final
  FilmRepositoryInterface filmRepositoryInterface;

  private final List<String> genres = List.of("Drama", "Action", "Thriller", "Horror", "Comedy", "Sci-Fi", "Fantasy", "Crime");
  ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public FilmDataServiceRepository(FilmRepositoryInterface filmRepositoryInterface) {


    this.filmRepositoryInterface = filmRepositoryInterface;
  }

  @Override
  public FilmModel getById(Long id) {

   /*FilmEntity entity = filmRepositoryInterface.findById(id).orElse(null);
    return new FilmModel(entity.getId(), entity.getTitle(),entity.getRating(),entity.getGenres()
    ,entity.getAuthors(),entity.getReleaseYear(),entity.getDurationMinutes(),entity.getDescription()
    , entity.getLanguage(), entity.getType(), entity.getCast(), entity.getCoverImageUrl());
 */
    FilmEntity entity = filmRepositoryInterface.findById(id).orElse(null);
    return modelMapper.map(entity,FilmModel.class);
  }

  @Override
  public List<FilmModel> getFilms() {
     Iterable<FilmEntity> filmEntities = filmRepositoryInterface.findAll();
     List<FilmModel> films = new ArrayList<>();
     filmEntities.forEach(film->films.add(modelMapper.map(film,FilmModel.class)));
     return films;
  }

  @Override
  public List<FilmModel> searchFilms(String searchTerm) {
    List<FilmEntity> filmEntities = filmRepositoryInterface.findByTitleContainingIgnoreCase(searchTerm);
    List<FilmModel> films = new ArrayList<>();
    filmEntities.forEach(film->films.add(modelMapper.map(film,FilmModel.class)));

    return films;
  }
  @Override
  public List<FilmModel> searchByGenres(String searchTerm) {
    List<FilmEntity> filmEntities = filmRepositoryInterface.findByGenresContainingIgnoreCase(searchTerm);
    List<FilmModel> films = new ArrayList<>();
    filmEntities.forEach(film->films.add(modelMapper.map(film,FilmModel.class)));

    return films;
  }

  @Override
  public Long addOne(FilmModel newFilm) {
    FilmEntity filmEntity = modelMapper.map(newFilm, FilmEntity.class);
    FilmEntity result = filmRepositoryInterface.save(filmEntity);
    if (result == null){
      return 0L;
    }else return 1L;
  }

  @Override
  public boolean deleteOne(Long id) {
    return filmRepositoryInterface.deleteFilmById(id);
  }

  @Override
  public FilmModel updateOne(Long id, FilmModel filmModel) {
    FilmEntity entity = modelMapper.map(filmModel, FilmEntity.class);
    FilmEntity result = filmRepositoryInterface.save(entity);
    return modelMapper.map(result,FilmModel.class);
  }
  @Override
  public List<FilmModel> getLatestFilms() {
    List<FilmEntity> filmEntities = filmRepositoryInterface.findTop6ByOrderByIdDesc();
    List<FilmModel> films = new ArrayList<>();
    filmEntities.forEach(film->films.add(modelMapper.map(film,FilmModel.class)));
    return films;
  }

  @Override
  public List<FilmModel> getSeries(){
    List<FilmEntity> filmEntities = filmRepositoryInterface.findByTypeContainingIgnoreCase("Series");
    List<FilmModel> series = new ArrayList<>();
    filmEntities.forEach(film->series.add(modelMapper.map(film,FilmModel.class)));
    return series;
  }

  @Override
  public List<FilmModel> getOnlyFilms(){
    List<FilmEntity> filmEntities = filmRepositoryInterface.findByTypeContainingIgnoreCase("Movies");
    List<FilmModel> series = new ArrayList<>();
    filmEntities.forEach(film->series.add(modelMapper.map(film,FilmModel.class)));
    return series;
  }

  @Override
  public List<GenreFilmsDto> getFilmsByGenres() {
    List<GenreFilmsDto> result = new ArrayList<>();

    for (String genre : genres){
      List<FilmEntity> films = filmRepositoryInterface.findByTypeContainingIgnoreCaseAndGenresContainingIgnoreCase("Movies",genre);
      result.add(new GenreFilmsDto(genre,films));
    }

    return result;
  }

  @Override
  public List<GenreSeriesDto> getSeriesByGenres() {
    List<GenreSeriesDto> result = new ArrayList<>();

    for (String genre : genres){
      List<FilmEntity> films = filmRepositoryInterface.findByTypeContainingIgnoreCaseAndGenresContainingIgnoreCase("Series",genre);
      result.add(new GenreSeriesDto(genre,films));
    }

    return result;
  }

  @Override
  public List<FilmModel> getHighestRated() {
    List<FilmEntity> allFilms = (List<FilmEntity>) filmRepositoryInterface.findAll();
    List<FilmEntity> sortedList = allFilms.stream()
        .sorted(Comparator.comparing(FilmEntity::getRating).reversed())
        .toList();
    List<FilmModel> sortedFilms = new ArrayList<>();
    sortedList.forEach(film->sortedFilms.add(modelMapper.map(film,FilmModel.class)));
    return sortedFilms;
  }

  @Override
  public List<FilmModel> getHighestRatedMovies() {
    List<FilmEntity> allFilms = (List<FilmEntity>) filmRepositoryInterface.findByTypeContainingIgnoreCase("Movies");
    List<FilmEntity> sortedList = allFilms.stream()
        .sorted(Comparator.comparing(FilmEntity::getRating).reversed())
        .toList();
    List<FilmModel> sortedFilms = new ArrayList<>();
    sortedList.forEach(film->sortedFilms.add(modelMapper.map(film,FilmModel.class)));
    return sortedFilms;
  }
  @Override
  public List<FilmModel> getHighestRatedSeries() {
    List<FilmEntity> allFilms = (List<FilmEntity>) filmRepositoryInterface.findByTypeContainingIgnoreCase("Series");
    List<FilmEntity> sortedList = allFilms.stream()
        .sorted(Comparator.comparing(FilmEntity::getRating).reversed())
        .toList();
    List<FilmModel> sortedFilms = new ArrayList<>();
    sortedList.forEach(film->sortedFilms.add(modelMapper.map(film,FilmModel.class)));
    return sortedFilms;
  }
}

