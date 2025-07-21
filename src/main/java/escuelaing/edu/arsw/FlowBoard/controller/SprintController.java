package escuelaing.edu.arsw.FlowBoard.controller;

import escuelaing.edu.arsw.FlowBoard.model.Sprint;
import escuelaing.edu.arsw.FlowBoard.service.SprintService;
import escuelaing.edu.arsw.FlowBoard.service.TaskService;
import escuelaing.edu.arsw.FlowBoard.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {
    private final SprintService sprintService;
    private final TaskService taskService;

    public SprintController(SprintService sprintService, TaskService taskService) {
        this.sprintService = sprintService;
        this.taskService = taskService;
    }
    // Endpoint para obtener tareas asociadas a un sprint
    @GetMapping("/{sprintId}/tasks")
    public ResponseEntity<List<Task>> getTasksBySprint(@PathVariable String sprintId) {
        return ResponseEntity.ok(taskService.getTasksBySprintId(sprintId));
    }

    // Endpoint para crear un nuevo sprint
    @PostMapping
    public ResponseEntity<Sprint> createSprint(@RequestBody Sprint sprint) {
        return ResponseEntity.ok(sprintService.createSprint(sprint));
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<Sprint>> getSprintsByBoard(@PathVariable String boardId) {
        return ResponseEntity.ok(sprintService.getSprintsByBoardId(boardId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable String id) {
        Optional<Sprint> sprint = sprintService.getSprintById(id);
        return sprint.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Sprint> updateSprint(@RequestBody Sprint sprint) {
        return ResponseEntity.ok(sprintService.updateSprint(sprint));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(@PathVariable String id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }
}
