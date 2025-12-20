package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.persistence.*;
import lombok.Getter;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

/**
 * Encja reprezentująca wskaźniki zdrowotne użytkownika (Health Metrics).
 * Przechowuje dane o parametrach fizycznych takich jak waga, wzrost czy tętno,
 * rejestrowane w konkretnych punktach czasowych.
 */
@Entity
@Table(name = "Health_Metrics")
@Getter
public class HealthMetrics {

    /** Unikalny identyfikator wpisu parametrów zdrowotnych. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** * Użytkownik, którego dotyczą wskaźniki zdrowotne.
     * Relacja wiele-do-jednego z encją {@link User}, ładowana leniwie (LAZY).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** Data dokonania pomiaru wskaźników zdrowotnych. */
    @Column(name = "date", nullable = false)
    private LocalDate date;

    /** Waga użytkownika w momencie pomiaru (np. w kilogramach). */
    @Column
    private Double weight;

    /** Wzrost użytkownika (np. w centymetrach). */
    @Column
    private Double height;

    /** Tętno spoczynkowe lub wysiłkowe zarejestrowane podczas pomiaru (BPM). */
    @Column(name = "heart_rate")
    private Integer heartRate;

    /**
     * Bezargumentowy konstruktor wymagany przez specyfikację JPA.
     */
    public HealthMetrics() {
    }

    /**
     * Konstruktor inicjalizujący kompletny wpis wskaźników zdrowotnych.
     *
     * @param user obiekt użytkownika
     * @param date data pomiaru
     * @param weight masa ciała
     * @param height wzrost
     * @param heartRate tętno (uderzenia na minutę)
     */
    public HealthMetrics(User user, LocalDate date, Double weight, Double height, Integer heartRate) {
        this.user = user;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }

}