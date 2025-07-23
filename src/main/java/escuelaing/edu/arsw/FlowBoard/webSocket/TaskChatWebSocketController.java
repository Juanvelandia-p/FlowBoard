package escuelaing.edu.arsw.FlowBoard.webSocket;

import escuelaing.edu.arsw.FlowBoard.model.Message;
import escuelaing.edu.arsw.FlowBoard.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class TaskChatWebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @Autowired
    public TaskChatWebSocketController(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    // Recibe mensajes de chat y los reenvía a todos los clientes suscritos a la tarea
    @MessageMapping("/task/chat")
    public void handleTaskChat(@Payload Message message) {
        Message saved = messageService.createMessage(message);
        messagingTemplate.convertAndSend("/topic/task-chat." + message.getTaskId(), saved);
    }

}
