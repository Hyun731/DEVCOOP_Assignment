package controller;

import service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) { //구글링
        this.userService = userService;
    }

    public void signUp(String id, String pw) {
        userService.signUp(id, pw);
    }

    public boolean login(String id, String pw) {
        return userService.login(id, pw);
    }
}
