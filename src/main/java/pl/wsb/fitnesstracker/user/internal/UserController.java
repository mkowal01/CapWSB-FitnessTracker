package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userProvider.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserDto> getSimpleUsers() {
        return userProvider.findAllUsers()
                .stream()
                .map(u -> new UserDto(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        null,
                        null
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userProvider.getUser(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return userMapper.toDto(user);
    }

    @GetMapping("/email")
    public List<UserDto> getByEmail(@RequestParam String email) {
        return userProvider.getUserByEmail(email)
                .map(userMapper::toDto)
                .map(List::of)
                .orElse(List.of());
    }

    @GetMapping("/older/{date}")
    public List<UserDto> olderThan(@PathVariable LocalDate date) {
        return userProvider.findAllUsers()
                .stream()
                .filter(u -> u.getBirthdate().isBefore(date))
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto dto) {
        userService.createUser(userMapper.toEntity(dto));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UserDto dto) {
        userService.updateUser(id, userMapper.toEntity(dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
