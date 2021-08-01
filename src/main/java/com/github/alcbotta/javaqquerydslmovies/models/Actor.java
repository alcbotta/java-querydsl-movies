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
@Table(name = "actors")
public class Actor {
    @Id
    @SequenceGenerator(initialValue = 1, name = "actor_id_gen", sequenceName = "actor_id_gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_id_gen")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}