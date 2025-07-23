package escuelaing.edu.arsw.FlowBoard.controller;

import escuelaing.edu.arsw.FlowBoard.model.Board;
import escuelaing.edu.arsw.FlowBoard.model.Team;
import escuelaing.edu.arsw.FlowBoard.model.User;
import escuelaing.edu.arsw.FlowBoard.service.BoardService;
import escuelaing.edu.arsw.FlowBoard.service.TeamService;
import escuelaing.edu.arsw.FlowBoard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService; // Asegúrate de tener este servicio

    // Endpoint para crear un nuevo equipo
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Map<String, Object> payload, Authentication auth) {
        String name = (String) payload.get("name");
        List<String> invitedEmails = (List<String>) payload.get("invitedEmails");
        String leaderEmail = auth.getName();
        User leader = userService.findByEmail(leaderEmail);

        Team team = new Team();
        team.setName(name);
        team.setLeaderId(leader.getId());
        team.setMemberIds(List.of(leader.getId()));
        team.setPendingInvitations(invitedEmails);

        Team savedTeam = teamService.createTeam(team);

        // Crear el tablero asociado al equipo
        Board board = new Board();
        board.setName(name + " Board");
        board.setTeamId(savedTeam.getId());
        boardService.createBoard(board);

        return ResponseEntity.ok(savedTeam);
    }

    // Endpoint para obtener equipos por ID de usuario
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    // Endpoint para obtener un equipo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable String id) {
        Optional<Team> team = teamService.getTeamById(id);
        return team.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para actualizar un equipo
    @PutMapping
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.updateTeam(team));
    }

    // Endpoint para eliminar un equipo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener equipos del usuario autenticado
    @GetMapping("/my")
    public ResponseEntity<List<Team>> getTeamsForCurrentUser(Authentication auth) {
        String email = auth.getName();
        List<Team> teams = teamService.getTeamsByUserEmail(email);
        return ResponseEntity.ok(teams);
    }

    // Endpoint para aceptar una invitación a un equipo
    @PostMapping("/{teamId}/accept-invitation")
    public ResponseEntity<?> acceptInvitation(@PathVariable String teamId, Authentication auth) {
        String email = auth.getName();
        try {
            teamService.acceptInvitation(teamId, email);
            return ResponseEntity.ok("Invitación aceptada");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para obtener equipos donde el usuario tiene invitación pendiente
    @GetMapping("/pending-invitations")
    public ResponseEntity<List<Team>> getPendingInvitations(Authentication auth) {
        String email = auth.getName();
        List<Team> pending = teamService.getTeamsWithPendingInvitation(email);
        return ResponseEntity.ok(pending);
    }
}
