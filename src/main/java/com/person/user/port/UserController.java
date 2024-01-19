package com.person.user.port;

import com.person.user.api.*;
import com.person.user.api.request.*;
import com.person.user.api.response.UserForm;
import com.person.user.api.response.UserGuideRow;
import com.person.platform.CommonUrl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping(CommonUrl.API)
public class UserController {

    private final UserBasicCommands commands;

    @Autowired
    public UserController(UserBasicCommands commands) {
        this.commands = commands;
    }

    @GetMapping(UserUrl.USERS)
    public List<UserGuideRow> getUserGuideRows() {
        return commands.getUserGuideRows();
    }

    @GetMapping(UserUrl.USERS_ID)
    public UserForm getUserForm(@PathVariable Long id) {
        return commands.getUserForm(new GetUserRequest(id));
    }

    @PostMapping(UserUrl.USERS)
    public Long createUser(@Valid @RequestBody CreateUserRequest request) {
        return commands.createUser(request);
    }

    @PutMapping(UserUrl.USERS_ID)
    public void updateUser(@PathVariable("id") Long id, @Valid @RequestBody CreateUserRequest request) {
        commands.updateUser(new UpdateUserRequest(id, request.name(), request.address(), request.description()));
    }

    @DeleteMapping(UserUrl.USERS_ID)
    public void deleteUser(@PathVariable("id") Long id) {
        commands.deleteUser(new DeleteUserRequest(id));
    }
}
