package escuelaing.edu.arsw.FlowBoard.repository;

import escuelaing.edu.arsw.FlowBoard.model.Message;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByTaskId(String taskId);
}
