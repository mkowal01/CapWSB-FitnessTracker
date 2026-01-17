package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;

/**
 * DTO used to expose Training data via REST API.
 */
public class TrainingDto {

    private Long id;
    private Long userId;
    private Date startTime;
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;

    public TrainingDto(
            Long id,
            Long userId,
            Date startTime,
            Date endTime,
            ActivityType activityType,
            double distance,
            double averageSpeed) {
        this.id = id;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Date getStartTime() { return startTime; }
    public Date getEndTime() { return endTime; }
    public ActivityType getActivityType() { return activityType; }
    public double getDistance() { return distance; }
    public double getAverageSpeed() { return averageSpeed; }
}
