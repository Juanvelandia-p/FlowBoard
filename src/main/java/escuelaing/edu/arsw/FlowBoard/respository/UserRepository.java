package escuelaing.edu.arsw.FlowBoard.respository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import escuelaing.edu.arsw.FlowBoard.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    
    // Method to find a user by their email
    User findByCorreo(String correo);
    
    // Method to find a user by their name
    User findByNombre(String nombre);
    
    // Method to find a user by their ID
    Optional findById(String id);
    
}
