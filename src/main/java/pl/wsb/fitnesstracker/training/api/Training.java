package pl.wsb.fitnesstracker.training.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

/**
 * Encja reprezentująca pojedynczą jednostkę treningową zapisaną w systemie.
 * Zawiera informacje o użytkowniku, czasie trwania, rodzaju aktywności oraz osiągniętych statystykach.
 */
@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    /** Unikalny identyfikator treningu w bazie danych. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** * Użytkownik, który wykonał dany trening.
     * Relacja wiele-do-jednego z encją {@link User}.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** Data i godzina rozpoczęcia treningu. */
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    /** Data i godzina zakończenia treningu. */
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    /** * Typ aktywności fizycznej (np. bieganie, jazda na rowerze).
     * Mapowany jako wartość porządkowa (ORDINAL) z wyliczenia {@link ActivityType}.
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    /** Całkowity dystans pokonany podczas treningu (np. w kilometrach). */
    @Column(name = "distance")
    private double distance;

    /** Średnia prędkość uzyskana podczas sesji treningowej. */
    @Column(name = "average_speed")
    private double averageSpeed;

    /**
     * Konstruktor inicjalizujący nowy obiekt treningu ze wszystkimi wymaganymi parametrami.
     *
     * @param user obiekt użytkownika wykonującego trening
     * @param startTime czas rozpoczęcia
     * @param endTime czas zakończenia
     * @param activityType rodzaj wykonywanej aktywności
     * @param distance pokonany dystans
     * @param averageSpeed osiągnięta średnia prędkość
     */
    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

}