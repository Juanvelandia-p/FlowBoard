package escuelaing.edu.arsw.FlowBoard.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "boards")
public class Board {
    
    @Id
    private String id;
    private String nombre;
    private String teamId; // ID del usuario que creó el board

    // puedes agregar más adelante: descripción, fechaCreación, etc.
}
