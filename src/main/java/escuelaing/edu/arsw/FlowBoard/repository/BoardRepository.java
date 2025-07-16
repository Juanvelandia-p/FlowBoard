package escuelaing.edu.arsw.FlowBoard.repository;

import escuelaing.edu.arsw.FlowBoard.model.Board;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardRepository extends MongoRepository<Board, String> {

    List<Board> findByTeamId(String teamId);
}
