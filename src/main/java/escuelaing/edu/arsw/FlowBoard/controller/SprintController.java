package escuelaing.edu.arsw.FlowBoard.controller;

import escuelaing.edu.arsw.FlowBoard.model.Sprint;
import escuelaing.edu.arsw.FlowBoard.service.SprintService;
import escuelaing.edu.arsw.FlowBoard.service.TaskService;
import escuelaing.edu.arsw.FlowBoard.model.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {
    private final SprintService sprintService;
    private final TaskService taskService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public SprintController(SprintService sprintService, TaskService taskService) {
        this.sprintService = sprintService;
        this.taskService = taskService;
    }
    // Endpoint para obtener tareas asociadas a un sprint
    @GetMapping("/{sprintId}/tasks")
    public ResponseEntity<List<Task>> getTasksBySprint(@PathVariable String sprintId) {
        return ResponseEntity.ok(taskService.getTasksBySprintId(sprintId));
    }

    @PostMapping
    public Sprint createSprint(@RequestBody Sprint sprint) {
        Sprint saved = sprintService.createSprint(sprint);
        // Notifica a todos los usuarios del board
        messagingTemplate.convertAndSend("/topic/board-sprints." + saved.getBoardId(), saved);
        return saved;
    }
    // Endpoint para obtener sprints por ID de tablero
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<Sprint>> getSprintsByBoard(@PathVariable String boardId) {
        return ResponseEntity.ok(sprintService.getSprintsByBoardId(boardId));
    }
    // Endpoint para obtener un sprint por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable String id) {
        Optional<Sprint> sprint = sprintService.getSprintById(id);
        return sprint.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Endpoint para actualizar un sprint
    @PutMapping
    public ResponseEntity<Sprint> updateSprint(@RequestBody Sprint sprint) {
        return ResponseEntity.ok(sprintService.updateSprint(sprint));
    }
    // Endpoint para eliminar un sprint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(@PathVariable String id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }
}
