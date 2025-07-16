package escuelaing.edu.arsw.FlowBoard.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "teams")
public class Team {
    @Id
    private String id;
    private String name;
    private String leaderId;
    private List<String> memberIds; // IDs de los usuarios
    // Puedes agregar descripción, fecha de creación, etc.
}
