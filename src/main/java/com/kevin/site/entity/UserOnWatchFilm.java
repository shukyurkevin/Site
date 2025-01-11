package com.kevin.site.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_on_watch_films")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserOnWatchFilm {

  @Id
  private Long id;

  @Column("user_id")
  private Long userId;

  @Column("film_id")
  private Long filmId;
}