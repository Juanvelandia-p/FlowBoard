package escuelaing.edu.arsw.FlowBoard.repository;

import escuelaing.edu.arsw.FlowBoard.model.Sprint;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SprintRepository extends MongoRepository<Sprint, String> {
    List<Sprint> findByBoardId(String boardId);
}
