package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.*;

import java.util.List;
import java.util.Optional;

@Service
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository repository;

    TrainingServiceImpl(TrainingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Training> getTraining(Long trainingId) {
        return repository.findById(trainingId);
    }

    @Override
    public List<TrainingDto> getAllTrainings() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<TrainingDto> getTrainingsByUser(Long userId) {
        return repository.findAll()
                .stream()
                .filter(t -> t.getUser().getId().equals(userId))
                .map(this::toDto)
                .toList();
    }

    private TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getUser().getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }
}
