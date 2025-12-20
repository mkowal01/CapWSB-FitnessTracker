package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.user.api.User;

/**
 * Encja reprezentująca powiązanie użytkownika z konkretnym wydarzeniem sportowym.
 * Klasa ta pełni rolę tabeli łączącej, pozwalając na rejestrację uczestnictwa
 * oraz śledzenie statusu zapisu danego użytkownika na wydarzenie.
 */
@Entity
@Table(name = "user_event",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "event_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEvent {

    /** Unikalny identyfikator rekordu uczestnictwa. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** * Referencja do użytkownika biorącego udział w wydarzeniu.
     * Wykorzystuje Lazy Fetching w celu optymalizacji wydajności.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** * Referencja do wydarzenia, na które zapisał się użytkownik.
     * Relacja obowiązkowa (optional = false).
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    /** * Status uczestnictwa użytkownika w wydarzeniu (np. "ZAPISANY", "POTWIERDZONY", "REZYGNACJA").
     */
    @Column(name = "status")
    @Setter
    private String status;
}