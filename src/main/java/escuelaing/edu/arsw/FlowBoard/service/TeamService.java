package escuelaing.edu.arsw.FlowBoard.service;

import escuelaing.edu.arsw.FlowBoard.model.Team;
import escuelaing.edu.arsw.FlowBoard.model.User;
import escuelaing.edu.arsw.FlowBoard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserService userService;

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(String id) {
        return teamRepository.findById(id);
    }

    public Team updateTeam(Team team) {
        return teamRepository.save(team);
    }

    public void deleteTeam(String id) {
        teamRepository.deleteById(id);
    }

    public void acceptInvitation(String teamId, String email) {
        User user = userService.findByEmail(email);
        if (user == null) throw new IllegalArgumentException("Usuario no encontrado");
        Team team = teamRepository.findById(teamId)
                .orElseThrow(NoSuchElementException::new);

        // Verifica si el usuario ya es miembro
        if (team.getMemberIds() != null && team.getMemberIds().contains(user.getId())) {
            throw new IllegalArgumentException("El usuario ya es miembro de este equipo");
        }

        if (team.getPendingInvitations() != null && team.getPendingInvitations().contains(email)) {
            team.getPendingInvitations().remove(email);
            team.getMemberIds().add(user.getId());
            teamRepository.save(team);
        } else {
            throw new IllegalArgumentException("No tienes invitación pendiente para este equipo");
        }
    }

    public List<Team> getTeamsWithPendingInvitation(String email) {
        return teamRepository.findByPendingInvitationsContaining(email);
    }

    public List<Team> getTeamsByUserEmail(String email) {
        User user = userService.findByEmail(email);
        if (user == null) return List.of();
        return teamRepository.findByMemberIdsContaining(user.getId());
    }
}
