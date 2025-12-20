package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

/**
 * Encja reprezentująca użytkownika w systemie Fitness Tracker.
 * Przechowuje dane osobowe, datę urodzenia oraz unikalny adres email.
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    /** Unikalny identyfikator użytkownika generowany automatycznie przez bazę danych. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /** Imię użytkownika. */
    @Column
    private String firstName;

    /** Nazwisko użytkownika. */
    @Column
    private String lastName;

    /** Data urodzenia użytkownika. */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /** Unikalny adres email użytkownika, wykorzystywany jako identyfikator w systemie. */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Konstruktor tworzący nowy obiekt użytkownika bez identyfikatora (używany przy rejestracji).
     *
     * @param firstName imię użytkownika
     * @param lastName nazwisko użytkownika
     * @param birthdate data urodzenia
     * @param email adres email (musi być unikalny)
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

    /**
     * Konstruktor tworzący obiekt użytkownika z istniejącym identyfikatorem (używany przy aktualizacji).
     *
     * @param id techniczny identyfikator bazy danych
     * @param firstName imię użytkownika
     * @param lastName nazwisko użytkownika
     * @param birthdate data urodzenia
     * @param email adres email
     */
    public User(
            final Long id,
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
}