package escuelaing.edu.arsw.FlowBoard.service;

import escuelaing.edu.arsw.FlowBoard.model.Board;
import escuelaing.edu.arsw.FlowBoard.repository.BoardRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getBoardsByTeamId(String teamId) {
        return boardRepository.findByTeamId(teamId);
    }

    public Optional<Board> getBoardById(String id) {
        return boardRepository.findById(id);
    }

    public Board updateBoard(Board board) {
        return boardRepository.save(board);
    }

    public void deleteBoard(String id) {
        boardRepository.deleteById(id);
    }
}
