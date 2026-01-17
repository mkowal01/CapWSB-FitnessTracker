package pl.wsb.fitnesstracker.training.api;

import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    Optional<Training> getTraining(Long trainingId);

    List<TrainingDto> getAllTrainings();

    List<TrainingDto> getTrainingsByUser(Long userId);
}
