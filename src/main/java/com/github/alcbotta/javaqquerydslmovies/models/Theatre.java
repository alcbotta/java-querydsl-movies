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
@Table(name = "theatres")
public class Theatre {

    @Id
    @SequenceGenerator(initialValue = 1, name = "theatre_id_gen", sequenceName = "theatre_id_gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "theatre_id_gen")
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;
}
