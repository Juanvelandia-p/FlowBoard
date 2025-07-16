package escuelaing.edu.arsw.FlowBoard.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "columns")
public class Column {
    @Id
    private String id;
    private String boardId;
    private String name;
    private int order;
    // Puedes agregar color, descripción, etc.
}
