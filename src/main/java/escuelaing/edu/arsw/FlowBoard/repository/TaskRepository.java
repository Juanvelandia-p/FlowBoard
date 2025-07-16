package escuelaing.edu.arsw.FlowBoard.repository;

import escuelaing.edu.arsw.FlowBoard.model.Task;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByBoardId(String boardId);

    List<Task> findByEstado(String estado);
}
