package escuelaing.edu.arsw.FlowBoard.controller;

import escuelaing.edu.arsw.FlowBoard.model.Task;
import escuelaing.edu.arsw.FlowBoard.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    // Endpoints para manejar tareas
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }
    // Endpoint para obtener todas las tareas
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<Task>> getTasksByBoard(@PathVariable String boardId) {
        return ResponseEntity.ok(taskService.getTasksByBoardId(boardId));
    }
    // Endpoint para obtener tareas por ID de sprint
    @GetMapping("/sprint/{sprintId}")
    public ResponseEntity<List<Task>> getTasksBySprint(@PathVariable String sprintId) {
        return ResponseEntity.ok(taskService.getTasksBySprintId(sprintId));
    }
    // Endpoint para obtener tareas por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Task>> getTasksByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(taskService.getTasksByEstado(estado));
    }
    // Endpoint para obtener una tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Endpoint para actualizar una tarea
    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(task));
    }
    // Endpoint para cambiar el estado de una tarea
    @PutMapping("/{id}/estado")
    public ResponseEntity<Task> changeTaskEstado(@PathVariable String id, @RequestBody String nuevoEstado) {
        return ResponseEntity.ok(taskService.changeTaskEstado(id, nuevoEstado));
    }
    // Endpoint para eliminar una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
