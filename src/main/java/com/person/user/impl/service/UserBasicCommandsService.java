package com.person.user.impl.service;

import com.person.user.api.UserBasicCommands;
import com.person.user.api.UserNotFoundException;
import com.person.user.api.request.CreateUserRequest;
import com.person.user.api.request.DeleteUserRequest;
import com.person.user.api.request.GetUserRequest;
import com.person.user.api.request.UpdateUserRequest;
import com.person.user.api.response.UserForm;
import com.person.user.api.response.UserGuideRow;
import com.person.user.impl.data.repository.UserRepository;
import com.person.user.impl.domain.User;
import com.person.user.impl.domain.UserId;
import com.person.platform.BusinessException;
import com.person.platform.Result;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserBasicCommandsService implements UserBasicCommands {

    private final UserRepository repository;

    @Autowired
    public UserBasicCommandsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserGuideRow> getUserGuideRows() {
        return Result.runCatching(() -> repository.findAll().stream()
                        .map(this::getGuideRow)
                        .toList())
                .onFailure(this::wrapInBusinessExceptionAndThrow)
                .orElseThrow();
    }

    @Override
    public UserForm getUserForm(GetUserRequest request) {
        return repository.findByUserId(new UserId(request.id()))
                .map(this::getForm)
                .onFailure(this::wrapInBusinessExceptionAndThrow)
                .orElseThrow();
    }

    @Override
    public Long createUser(CreateUserRequest request) {
        return Result.runCatching(repository::generateId)
                .map(id -> User.create(id, request))
                .onSuccess(repository::add)
                .onFailure(this::wrapInBusinessExceptionAndThrow)
                .map(User::id)
                .map(UserId::value)
                .orElseThrow();
    }

    @Override
    public void updateUser(UpdateUserRequest request) {
        repository.findByUserId(new UserId(request.id()))
                .map(person -> person.update(request))
                .onSuccess(repository::add)
                .onFailure(this::wrapInBusinessExceptionAndThrow)
                .orElseThrow();
    }

    @Override
    public void deleteUser(DeleteUserRequest request) {
        repository.findByUserId(new UserId(request.id()))
                .onSuccess(repository::remove)
                .onFailure(this::wrapInBusinessExceptionAndThrow)
                .orElseThrow();
    }

    private UserGuideRow getGuideRow(User person) {
        return new UserGuideRow(
                person.id().value(),
                person.name().value(),
                person.address().value()
        );
    }

    private UserForm getForm(User person) {
        return new UserForm(
                person.id().value(),
                person.name().value(),
                person.address().value(),
                person.description().value()
        );
    }

    private void wrapInBusinessExceptionAndThrow(RuntimeException exception) {
        switch (exception) {
            case NullPointerException e -> throw new BusinessException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            case IllegalArgumentException e -> throw new BusinessException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            case UserNotFoundException e -> throw new BusinessException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            default -> throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }
}
