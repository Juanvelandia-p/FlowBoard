package escuelaing.edu.arsw.FlowBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import escuelaing.edu.arsw.FlowBoard.model.User;
import escuelaing.edu.arsw.FlowBoard.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Method to register a new user
    public void registerUser(String nombre, String correo, String contrasena) {
        if (userRepository.findByCorreo(correo) != null) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        User newUser = new User();
        newUser.setNombre(nombre);
        newUser.setCorreo(correo);
        // Encriptar la contraseña antes de guardar
        newUser.setContrasena(passwordEncoder.encode(contrasena));
        userRepository.save(newUser);
    }

    // Method to find a user by email
    public User findByEmail(String email) {
        // Find the user by email
        return userRepository.findByCorreo(email);
    }
}
