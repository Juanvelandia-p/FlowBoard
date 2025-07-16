package escuelaing.edu.arsw.FlowBoard.repository;

import escuelaing.edu.arsw.FlowBoard.model.Column;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColumnRepository extends MongoRepository<Column, String> {
}
