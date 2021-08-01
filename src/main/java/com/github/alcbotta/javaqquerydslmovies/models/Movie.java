package com.github.alcbotta.javaqquerydslmovies.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "registrations")
public class Movie {
    @Id
    @SequenceGenerator(initialValue = 1, name = "movie_id_gen", sequenceName = "movie_id_gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_gen")
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;
}
