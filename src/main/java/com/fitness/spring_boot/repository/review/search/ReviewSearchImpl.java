package com.fitness.spring_boot.repository.review.search;

import com.fitness.spring_boot.domain.review.QReview;
import com.fitness.spring_boot.domain.review.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ReviewSearchImpl extends QuerydslRepositorySupport implements ReviewSearch{
    public ReviewSearchImpl(Class<?> domainClass) {
        super(domainClass);
    }

    @Override
    public Page<Review> searchAll(String[] types, String keyword, Pageable pageable) {
        QReview review = QReview.review;
        JPQLQuery<Review> query = this.from(review);
        if(types != null && types.length>0 && keyword !=null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for(String type:types) {
                switch (type){
                    case "t":
                    booleanBuilder.or(review.title.contains(keyword));
                    break;
                    case "c":
                        booleanBuilder.or(review.title.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(review.title.contains(keyword));
                }
            }//end for
            query.where(new Predicate[]{booleanBuilder});
        }//end if
        query.where(review.rno.gt(0L));
        this.getQuerydsl().applyPagination(pageable,query);
        List<Review> list = query.fetch();
        Long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }
}
