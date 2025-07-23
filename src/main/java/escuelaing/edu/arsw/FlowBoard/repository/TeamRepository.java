package escuelaing.edu.arsw.FlowBoard.repository;

import escuelaing.edu.arsw.FlowBoard.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, String> {
    List<Team> findByPendingInvitationsContaining(String email);
    List<Team> findByMemberIdsContaining(String userId);
}
