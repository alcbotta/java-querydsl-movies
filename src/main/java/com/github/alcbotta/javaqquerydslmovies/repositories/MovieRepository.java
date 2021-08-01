package com.github.alcbotta.javaqquerydslmovies.repositories;

import com.github.alcbotta.javaqquerydslmovies.models.Movie;

import com.github.alcbotta.javaqquerydslmovies.models.QMovie;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;


public interface MovieRepository extends JpaRepository<Movie, Long>,
        QuerydslPredicateExecutor<Movie>, QuerydslBinderCustomizer<QMovie> {

    @Override
    default void customize(QuerydslBindings bindings, QMovie movie) {
        bindings.excluding(movie.id);

        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
