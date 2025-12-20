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

/**
 * Kontroler REST odpowiedzialny za obsługę operacji na użytkownikach.
 * Zapewnia dostęp do danych poprzez protokół HTTP zgodnie z architekturą REST.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    /**
     * Pobiera listę wszystkich użytkowników zarejestrowanych w systemie.
     *
     * @return lista wszystkich użytkowników jako obiekty {@link UserDto}
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userProvider.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Pobiera uproszczoną listę użytkowników, zawierającą tylko podstawowe dane (ID, imię, nazwisko).
     * Pola data urodzenia oraz email są w tym widoku pomijane (null).
     *
     * @return lista uproszczonych obiektów {@link UserDto}
     */
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

    /**
     * Pobiera szczegółowe dane użytkownika na podstawie jego unikalnego identyfikatora ID.
     *
     * @param id techniczny identyfikator użytkownika
     * @return dane użytkownika w formacie {@link UserDto}
     * @throws IllegalArgumentException jeśli użytkownik o podanym ID nie istnieje
     */
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userProvider.getUser(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return userMapper.toDto(user);
    }

    /**
     * Wyszukuje użytkownika na podstawie adresu e-mail.
     *
     * @param email adres e-mail poszukiwanego użytkownika
     * @return lista zawierająca znalezionego użytkownika lub lista pusta
     */
    @GetMapping("/email")
    public List<UserDto> getByEmail(@RequestParam String email) {
        return userProvider.getUserByEmail(email)
                .map(userMapper::toDto)
                .map(List::of)
                .orElse(List.of());
    }

    /**
     * Wyszukuje użytkowników urodzonych przed wskazaną datą.
     *
     * @param date graniczna data urodzenia
     * @return lista użytkowników spełniających kryterium wieku
     */
    @GetMapping("/older/{date}")
    public List<UserDto> olderThan(@PathVariable LocalDate date) {
        return userProvider.findAllUsers()
                .stream()
                .filter(u -> u.getBirthdate().isBefore(date))
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Tworzy i zapisuje nowego użytkownika w systemie.
     *
     * @param dto dane nowego użytkownika przekazane w ciele żądania
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto dto) {
        userService.createUser(userMapper.toEntity(dto));
    }

    /**
     * Aktualizuje dane istniejącego użytkownika.
     *
     * @param id identyfikator użytkownika do aktualizacji
     * @param dto nowe dane użytkownika
     */
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UserDto dto) {
        userService.updateUser(id, userMapper.toEntity(dto));
    }

    /**
     * Usuwa użytkownika z systemu na podstawie identyfikatora ID.
     *
     * @param id identyfikator użytkownika do usunięcia
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}