package escuelaing.edu.arsw.FlowBoard.controller;

import escuelaing.edu.arsw.FlowBoard.model.Board;
import escuelaing.edu.arsw.FlowBoard.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // Endpoint para crear un nuevo tablero
    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        return ResponseEntity.ok(boardService.createBoard(board));
    }

    // Endpoint para obtener tableros por ID de equipo
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Board>> getBoardsByTeam(@PathVariable String teamId) {
        return ResponseEntity.ok(boardService.getBoardsByTeamId(teamId));
    }

    // Endpoint para obtener un tablero por ID
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable String id) {
        Optional<Board> board = boardService.getBoardById(id);
        return board.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para actualizar un tablero
    @PutMapping
    public ResponseEntity<Board> updateBoard(@RequestBody Board board) {
        return ResponseEntity.ok(boardService.updateBoard(board));
    }

    // Endpoint para eliminar un tablero
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable String id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
