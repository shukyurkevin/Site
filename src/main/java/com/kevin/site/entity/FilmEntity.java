package com.kevin.site.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

  @Table("movies")
  public class FilmEntity {
    @Id
    @Column("Id")
    private int id;
    @Column("Title")
    private String title;
    @Column("Rating")
    private float rating;
    @Column("Genres")
    private String genres;
    @Column("Authors")
    private String authors;
    @Column("ReleaseYear")
    private int releaseYear;
    @Column("DurationMinutes")
    private int durationMinutes;
    @Column("Description")
    private String description;
    @Column("Language")
    private String language;
    @Column("Type")
    private String type;
    @Column("Cast")
    private String cast;
    @Column("CoverImageURL")
    private String coverImageUrl;
    @Column("FilmImageURL")
    private String filmImageUrl;
    @Column("VideoURL")
    private String videoURL;
    @Column("AgeRating")
    private String ageRating;
    @Column("Seasons")
    private int seasons;
    @Column("Episodes")
    private int episodes;
  public FilmEntity(){

  }

    @Override
    public String toString() {
      return "FilmEntity{" +
          "id=" + id +
          ", title='" + title + '\'' +
          ", rating=" + rating +
          ", genres='" + genres + '\'' +
          ", authors='" + authors + '\'' +
          ", releaseYear=" + releaseYear +
          ", durationMinutes=" + durationMinutes +
          ", description='" + description + '\'' +
          ", language='" + language + '\'' +
          ", type='" + type + '\'' +
          ", cast='" + cast + '\'' +
          ", coverImageUrl='" + coverImageUrl + '\'' +
          ", filmImageUrl='" + filmImageUrl + '\'' +
          ", videoURL='" + videoURL + '\'' +
          ", ageRating='" + ageRating + '\'' +
          ", seasons=" + seasons +
          ", episodes=" + episodes +
          '}';
    }

    public FilmEntity(int id, String title, float rating, String genres, String authors,
                      int releaseYear, int durationMinutes, String description, String language,
                      String type, String cast, String coverImageUrl, String filmImageUrl,
                      String videoURL, String ageRating, int seasons, int episodes) {
      this.id = id;
      this.title = title;
      this.rating = rating;
      this.genres = genres;
      this.authors = authors;
      this.releaseYear = releaseYear;
      this.durationMinutes = durationMinutes;
      this.description = description;
      this.language = language;
      this.type = type;
      this.cast = cast;
      this.coverImageUrl = coverImageUrl;
      this.filmImageUrl = filmImageUrl;
      this.videoURL = videoURL;
      this.ageRating = ageRating;
      this.seasons = seasons;
      this.episodes = episodes;
    }

    public int getEpisodes() {
      return episodes;
    }

    public void setEpisodes(int episodes) {
      this.episodes = episodes;
    }

    public int getSeasons() {
      return seasons;
    }

    public void setSeasons(int seasons) {
      this.seasons = seasons;
    }

    public String getAgeRating() {
    return ageRating;
  }

  public void setAgeRating(String ageRating) {
    this.ageRating = ageRating;
  }

  public String getFilmImageUrl() {
    return filmImageUrl;
  }

  public void setFilmImageUrl(String filmImageUrl) {
    this.filmImageUrl = filmImageUrl;
  }

  public String getVideoURL() {
    return videoURL;
  }

  public void setVideoURL(String videoURL) {
    this.videoURL = videoURL;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public String getGenres() {
    return genres;
  }

  public void setGenres(String genres) {
    this.genres = genres;
  }

  public String getAuthors() {
    return authors;
  }

  public void setAuthors(String authors) {
    this.authors = authors;
  }

  public int getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(int releaseYear) {
    this.releaseYear = releaseYear;
  }

  public int getDurationMinutes() {
    return durationMinutes;
  }

  public void setDurationMinutes(int durationMinutes) {
    this.durationMinutes = durationMinutes;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCast() {
    return cast;
  }

  public void setCast(String cast) {
    this.cast = cast;
  }

  public String getCoverImageUrl() {
    return coverImageUrl;
  }

  public void setCoverImageUrl(String coverImageUrl) {
    this.coverImageUrl = coverImageUrl;
  }
}
