package com.github.alcbotta.javaqquerydslmovies.repositories;

import com.github.alcbotta.javaqquerydslmovies.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository <Actor, Long> {
}
