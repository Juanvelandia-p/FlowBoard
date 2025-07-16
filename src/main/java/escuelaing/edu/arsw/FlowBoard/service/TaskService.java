package escuelaing.edu.arsw.FlowBoard.service;

import escuelaing.edu.arsw.FlowBoard.model.Task;
import escuelaing.edu.arsw.FlowBoard.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasksByBoardId(String boardId) {
        return taskRepository.findByBoardId(boardId);
    }

    public List<Task> getTasksBySprintId(String sprintId) {
        return taskRepository.findAll().stream()
            .filter(task -> sprintId.equals(task.getSprintId()))
            .toList();
    }

    public List<Task> getTasksByEstado(String estado) {
        return taskRepository.findByEstado(estado);
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public Task changeTaskEstado(String id, String nuevoEstado) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setEstado(nuevoEstado);
            return taskRepository.save(task);
        }
        throw new IllegalArgumentException("Task not found");
    }
}
