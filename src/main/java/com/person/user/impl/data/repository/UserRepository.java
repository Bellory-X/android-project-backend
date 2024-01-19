package com.person.user.impl.data.repository;

import com.person.user.api.UserNotFoundException;
import com.person.user.impl.data.dao.UserDao;
import com.person.user.impl.data.mapper.UserDbMapper;
import com.person.user.impl.domain.User;
import com.person.user.impl.domain.UserId;
import com.person.platform.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final UserDao dao;
    private final UserDbMapper mapper;

    @Autowired
    public UserRepository(UserDao dao, UserDbMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public UserId generateId() {
        return new UserId(dao.nextId());
    }

    public List<User> findAll() {
        return dao.findAll().stream()
                .map(mapper::map)
                .toList();
    }

    public Result<User> findByUserId(UserId userId) {
        return dao.findById(userId.value())
                .map(mapper::map)
                .map(Result::success)
                .orElseGet(() -> Result.failure(
                        new UserNotFoundException("Person with id=%d not found".formatted(userId.value()))
                ));
    }

    public void add(User user) {
        dao.save(mapper.map(user));
    }

    public void remove(User user) {
        dao.deleteById(user.id().value());
    }
}
