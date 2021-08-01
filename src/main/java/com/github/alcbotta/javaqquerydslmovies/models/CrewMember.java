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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "crew_members")
public class CrewMember {
    @Id
    @SequenceGenerator(initialValue = 1, name = "crew_members_id_gen", sequenceName = "crew_members_id_gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crew_members_id_gen")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = true)
    private String role;
}
