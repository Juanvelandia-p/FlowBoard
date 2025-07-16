package escuelaing.edu.arsw.FlowBoard.controller;

import escuelaing.edu.arsw.FlowBoard.model.Task;
import escuelaing.edu.arsw.FlowBoard.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<Task>> getTasksByBoard(@PathVariable String boardId) {
        return ResponseEntity.ok(taskService.getTasksByBoardId(boardId));
    }

    @GetMapping("/sprint/{sprintId}")
    public ResponseEntity<List<Task>> getTasksBySprint(@PathVariable String sprintId) {
        return ResponseEntity.ok(taskService.getTasksBySprintId(sprintId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Task>> getTasksByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(taskService.getTasksByEstado(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Task> changeTaskEstado(@PathVariable String id, @RequestBody String nuevoEstado) {
        return ResponseEntity.ok(taskService.changeTaskEstado(id, nuevoEstado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
