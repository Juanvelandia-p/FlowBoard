package escuelaing.edu.arsw.FlowBoard.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuarios")
public class User {
    @Id
    private String id;
    private String nombre;
    private String correo;
    private String contrasena;
    private List<String> roles; // Ejemplo: ["USER"], ["ADMIN"]
}
