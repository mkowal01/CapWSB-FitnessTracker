package pl.wsb.fitnesstracker.training.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing Training API.
 */
@RestController
@RequestMapping("/trainings")
class TrainingController {

    private final TrainingProvider trainingProvider;

    TrainingController(TrainingProvider trainingProvider) {
        this.trainingProvider = trainingProvider;
    }

    @GetMapping
    List<TrainingDto> getAllTrainings() {
        return trainingProvider.getAllTrainings();
    }

    @GetMapping("/user/{userId}")
    List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingProvider.getTrainingsByUser(userId);
    }
}
