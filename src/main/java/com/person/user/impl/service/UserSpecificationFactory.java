package com.person.user.impl.service;

import com.person.user.api.request.UserGuideFilter;
import com.person.user.impl.data.model.UserDbModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.person.platform.SpecificationUtils.substringIgnoringCase;

@Service
public class UserSpecificationFactory {

    public UserSpecification build(UserGuideFilter filter) {
        return new UserSpecification(
                filter.pageSize(),
                filter.pageNumber(),
                jpaSpecificationOf(filter)
        );
    }

    public Specification<UserDbModel> jpaSpecificationOf(UserGuideFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.search() != null) {
                List<Predicate> searchPredicates = new ArrayList<>();

                searchPredicates.add(substringIgnoringCase(criteriaBuilder, root.get("name"), filter.search()));
                searchPredicates.add(substringIgnoringCase(criteriaBuilder, root.get("address"), filter.search()));

                predicates.add(criteriaBuilder.or(searchPredicates.toArray(Predicate[]::new)));
            }

//            filter.id().ifPresent(id -> predicates.add(criteriaBuilder.like(root.get("id"), substringPattern(id.toString()))));
//
//            filter.name().ifPresent(name -> predicates.add(
//                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), substringPattern(name.toLowerCase()))
//            ));
//
//            filter.address().ifPresent(address -> predicates.add(
//                    criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), substringPattern(address.toLowerCase()))
//            ));
//
//            filter.sortedField().ifPresent(sortedColumn -> {
//                Order order = switch (sortedColumn.sortOrder()) {
//                    case ASC -> criteriaBuilder.asc(root.get(sortedColumn.sortedField().getName()));
//                    case DSC -> criteriaBuilder.desc(root.get(sortedColumn.sortedField().getName()));
//                };
//                query.orderBy(order);
//            });

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
