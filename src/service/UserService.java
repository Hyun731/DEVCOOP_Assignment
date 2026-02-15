package service;

import domain.UserDomain;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { //구글링
        this.userRepository = userRepository;
    }

    public void signUp(String id, String pw) {
        if (userRepository.existsById(id)) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        UserDomain user = UserDomain.create(id, pw);
        userRepository.save(user);
    }

    public boolean login(String id, String pw) {
        return userRepository.findById(id)
                .map(user -> user.isPassword(pw))
                .orElse(false);
    }
}
