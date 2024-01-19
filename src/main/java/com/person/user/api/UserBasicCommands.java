package com.person.user.api;

import com.person.user.api.request.*;
import com.person.user.api.response.UserForm;
import com.person.user.api.response.UserGuideRow;

import java.util.List;

public interface UserBasicCommands {

    List<UserGuideRow> getUserGuideRows();

    UserForm getUserForm(GetUserRequest request);

    Long createUser(CreateUserRequest request);

    void updateUser(UpdateUserRequest request);

    void deleteUser(DeleteUserRequest request);
}
