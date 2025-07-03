package escuelaing.edu.arsw.FlowBoard.respository;

import java.util.List;

import escuelaing.edu.arsw.FlowBoard.model.Board;

public interface BoardRepository extends org.springframework.data.mongodb.repository.MongoRepository<Board, String> {
    
    // Method to find a board by its name
    Board findByNombre(String nombre);
    
    // Method to find a board by its ID
    java.util.Optional<Board> findById(String id);

    List<Board> findByTeamId(String teamId);
    
}
