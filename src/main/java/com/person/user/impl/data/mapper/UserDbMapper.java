package com.person.user.impl.data.mapper;

import com.person.user.impl.data.model.UserDbModel;
import com.person.user.impl.domain.*;
import org.springframework.stereotype.Component;

@Component
public class UserDbMapper {

    public User map(UserDbModel dbModel) {
        return new User(
                new UserId(dbModel.getId()),
                new Name(dbModel.getName()),
                new Address(dbModel.getAddress()),
                new Description(dbModel.getDescription())
        );
    }

    public UserDbModel map(User domainModel) {
        return new UserDbModel(
                domainModel.id().value(),
                domainModel.name().value(),
                domainModel.address().value(),
                domainModel.description().value()
        );
    }
}
