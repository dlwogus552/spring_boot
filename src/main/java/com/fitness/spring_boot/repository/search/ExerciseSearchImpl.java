package com.fitness.spring_boot.repository.search;

import com.fitness.spring_boot.domain.Exercise;
import com.fitness.spring_boot.domain.QExercise;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ExerciseSearchImpl extends QuerydslRepositorySupport implements ExerciseSearch{

    public ExerciseSearchImpl() {
        super(Exercise.class);
    }

    @Override
    public Page<Exercise> searchAll(String type, String keyword, Pageable pageable) {
        QExercise exercise=QExercise.exercise;
        JPQLQuery<Exercise> query = this.from(exercise);

        if(type !=null && keyword!=null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(exercise.title.contains(keyword));
            booleanBuilder.or(exercise.part.contains(type));
            query.where(new Predicate[]{booleanBuilder});
        }//end if
        query.where(exercise.eno.gt(0L));
        this.getQuerydsl().applyPagination(pageable,query);
        List<Exercise> list = query.fetch();
        Long count = query.fetchCount();
        return new PageImpl<>(list,pageable,count);
    }
}
