package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Encja reprezentująca wydarzenie sportowe (Event) w systemie.
 * Przechowuje informacje o planowanych zawodach, maratonach lub innych aktywnościach grupowych,
 * wraz z ich lokalizacją oraz ramami czasowymi.
 */
@Entity
@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    /** Unikalny identyfikator wydarzenia w bazie danych. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nazwa wydarzenia (np. "Maraton Warszawski"). */
    @Column(name = "name", nullable = false)
    private String name;

    /** * Szczegółowy opis wydarzenia.
     * Mapowany na typ tekstowy (TEXT) w bazie danych dla przechowywania długich treści.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /** Data i godzina rozpoczęcia wydarzenia. */
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    /** Data i godzina zakończenia wydarzenia. */
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    /** Kraj, w którym odbywa się wydarzenie. */
    @Column(name = "country")
    private String country;

    /** Miasto, w którym odbywa się wydarzenie. */
    @Column(name = "city")
    private String city;

}