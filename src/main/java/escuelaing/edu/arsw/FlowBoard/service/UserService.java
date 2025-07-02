package escuelaing.edu.arsw.FlowBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import escuelaing.edu.arsw.FlowBoard.model.User;
import escuelaing.edu.arsw.FlowBoard.respository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Method to register a new user
    public void registerUser(String nombre, String correo, String contrasena) {
        // Check if the user already exists
        if (userRepository.findByCorreo(correo) != null) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        
        // Create a new user object
        User newUser = new User();
        newUser.setNombre(nombre);
        newUser.setCorreo(correo);
        newUser.setContrasena(contrasena);
        
        // Save the user to the repository
        userRepository.save(newUser);
    }

    // Method to log in a user
    public String logIn(String correo, String contrasena) {
        // Find the user by email
        User user = userRepository.findByCorreo(correo);
        
        // Check if the user exists and the password matches
        if (user == null || !user.getContrasena().equals(contrasena)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        
        // Generate a token (for simplicity, we return the user's ID as a token)
        return user.getId().toString();
    }
}
