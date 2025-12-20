package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import java.time.LocalDate;

/**
 * Obiekt transferu danych (DTO) reprezentujący użytkownika.
 * Służy do komunikacji z API sieciowym w formacie JSON.
 * * @param id unikalny identyfikator użytkownika (może być null przy tworzeniu)
 * @param firstName imię użytkownika
 * @param lastName nazwisko użytkownika
 * @param birthdate data urodzenia w formacie yyyy-MM-dd
 * @param email adres email użytkownika
 */
public record UserDto(
        @Nullable Long id,
        String firstName,
        String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        String email) {
}