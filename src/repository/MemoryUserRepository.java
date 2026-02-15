package repository;

import domain.UserDomain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {
    private final Map<String, UserDomain> users = new HashMap<>();

    @Override
    public Optional<UserDomain> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public void save(UserDomain user) {
        users.put(user.getId(), user);
    }

    @Override
    public boolean existsById(String id) {
        return users.containsKey(id);
    }
}
