package com.github.alcbotta.javaqquerydslmovies.repositories;

import com.github.alcbotta.javaqquerydslmovies.models.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewMemberRepository  extends JpaRepository <CrewMember, Long> {
}
