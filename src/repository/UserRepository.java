package repository;

import domain.UserDomain;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDomain> findById(String id);
    void save(UserDomain user);
    boolean existsById(String id);
}
