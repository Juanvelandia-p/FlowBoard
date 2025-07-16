package escuelaing.edu.arsw.FlowBoard.service;

import escuelaing.edu.arsw.FlowBoard.model.Sprint;
import escuelaing.edu.arsw.FlowBoard.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SprintService {
    private final SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public Sprint createSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    public List<Sprint> getSprintsByBoardId(String boardId) {
        return sprintRepository.findByBoardId(boardId);
    }

    public Optional<Sprint> getSprintById(String id) {
        return sprintRepository.findById(id);
    }

    public Sprint updateSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    public void deleteSprint(String id) {
        sprintRepository.deleteById(id);
    }
}
