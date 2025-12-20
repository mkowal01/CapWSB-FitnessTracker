package pl.wsb.fitnesstracker.user.api;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);

    List<User> findAllUsers();
}
