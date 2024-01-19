package com.person.user.impl.service;

import com.person.user.impl.data.model.UserDbModel;
import org.springframework.data.jpa.domain.Specification;

public record UserSpecification(
        Integer pageSize,
        Integer pageNumber,
        Specification<UserDbModel> jpaSpecification
) {
}
