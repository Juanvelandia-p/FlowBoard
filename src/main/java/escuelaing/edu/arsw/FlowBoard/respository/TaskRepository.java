package escuelaing.edu.arsw.FlowBoard.respository;

import java.util.List;

import escuelaing.edu.arsw.FlowBoard.model.Task;

public interface TaskRepository extends org.springframework.data.mongodb.repository.MongoRepository<Task, String> {

    List<Task> findByEstado(String estado);
    List<Task> findByBoardId(String boardId);
}
