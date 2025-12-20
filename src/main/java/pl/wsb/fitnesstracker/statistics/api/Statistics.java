package pl.wsb.fitnesstracker.statistics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

/**
 * Encja reprezentująca zbiorcze statystyki aktywności fizycznej użytkownika.
 * Przechowuje zsumowane dane dotyczące wszystkich odbytych treningów, dystansu oraz spalonych kalorii.
 */
@Entity
@Table(name = "Statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    /** Unikalny identyfikator rekordu statystyk. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /** * Użytkownik, do którego przypisane są statystyki.
     * Relacja jeden-do-jednego z encją {@link User}.
     * Wykorzystuje opóźnione ładowanie (Lazy Fetching) dla optymalizacji wydajności.
     */
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    /** Łączna liczba wszystkich treningów przeprowadzonych przez użytkownika. */
    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    /** Całkowity dystans pokonany przez użytkownika we wszystkich sesjach treningowych. */
    @Column(name = "total_distance")
    private double totalDistance;

    /** Łączna liczba kalorii spalonych przez użytkownika. */
    @Column(name = "total_calories_burned")
    private int totalCaloriesBurned;

    /**
     * Konstruktor inicjalizujący pełny obiekt statystyk.
     *
     * @param id identyfikator techniczny (może być null przy tworzeniu nowego rekordu)
     * @param user obiekt użytkownika, którego dotyczą statystyki
     * @param totalTrainings suma wszystkich treningów
     * @param totalDistance suma pokonanego dystansu
     * @param totalCaloriesBurned suma spalonych kalorii
     */
    public Statistics(@Nullable Long id, User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.id = id;
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}