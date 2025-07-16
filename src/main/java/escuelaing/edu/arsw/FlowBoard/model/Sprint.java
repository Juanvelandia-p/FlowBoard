package escuelaing.edu.arsw.FlowBoard.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sprints")
public class Sprint {
    @Id
    private String id;
    private String boardId; // ID del tablero al que pertenece el sprint
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String objetivo;
}
