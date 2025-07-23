package escuelaing.edu.arsw.FlowBoard.controller;

import escuelaing.edu.arsw.FlowBoard.model.Message;
import escuelaing.edu.arsw.FlowBoard.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Endpoint para crear un nuevo mensaje
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.createMessage(message));
    }
    // Endpoint para obtener mensajes por ID de tarea
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Message>> getMessagesByTask(@PathVariable String taskId) {
        return ResponseEntity.ok(messageService.getMessagesByTaskId(taskId));
    }
    // Endpoint para obtener todos los mensajes
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable String id) {
        Optional<Message> message = messageService.getMessageById(id);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Endpoint para actualizar un mensaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
