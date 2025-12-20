package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * Komponent mapujący, odpowiedzialny za konwersję między encją {@link User}
 * a obiektem transferu danych {@link UserDto}.
 * Zapewnia izolację warstwy biznesowej od warstwy prezentacji.
 */
@Component
class UserMapper {

    /**
     * Przekształca encję użytkownika na obiekt DTO.
     *
     * @param user obiekt encji pobrany z bazy danych
     * @return obiekt DTO gotowy do wysłania przez API
     */
    UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }

    /**
     * Przekształca obiekt DTO na encję użytkownika.
     *
     * @param dto obiekt transferu danych otrzymany z API
     * @return obiekt encji User gotowy do zapisu w bazie danych
     */
    User toEntity(UserDto dto) {
        return new User(
                dto.firstName(),
                dto.lastName(),
                dto.birthdate(),
                dto.email()
        );
    }
}