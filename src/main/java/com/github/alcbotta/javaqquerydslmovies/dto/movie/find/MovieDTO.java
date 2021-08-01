package com.github.alcbotta.javaqquerydslmovies.dto.movie.find;

import com.github.alcbotta.javaqquerydslmovies.models.Actor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MovieDTO {
    private Long id;
    private String name;
    private Set<Actor> actors;
}
