package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDateTime;

/**
 * Encja reprezentująca konkretną sesję treningową (workout session).
 * Przechowuje szczegółowe dane techniczne dotyczące czasu trwania oraz
 * współrzędnych geograficznych (GPS) sesji.
 */
@Entity
@Table(name = "workout_session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutSession {

    /** Unikalny identyfikator sesji treningowej. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** * Relacja do głównego obiektu treningu.
     * Wykorzystuje Lazy Fetching w celu optymalizacji wydajności bazy danych.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    /** Znacznik czasowy określający moment rejestracji danych sesji. */
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    /** Początkowa szerokość geograficzna sesji. */
    @Column(name = "start_latitude", nullable = false)
    private double startLatitude;

    /** Początkowa długość geograficzna sesji. */
    @Column(name = "start_longitude", nullable = false)
    private double startLongitude;

    /** Końcowa szerokość geograficzna sesji (opcjonalna). */
    @Column(name = "end_latitude")
    private Double endLatitude;

    /** Końcowa długość geograficzna sesji (opcjonalna). */
    @Column(name = "end_longitude")
    private Double endLongitude;

    /** Wysokość nad poziomem morza zarejestrowana podczas sesji. */
    @Column(name = "altitude")
    private Double altitude;

}