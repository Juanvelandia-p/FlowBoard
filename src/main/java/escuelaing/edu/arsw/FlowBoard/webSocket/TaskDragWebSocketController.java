package escuelaing.edu.arsw.FlowBoard.webSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class TaskDragWebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(TaskDragWebSocketController.class);
    private final SimpMessagingTemplate messagingTemplate;

    public TaskDragWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Cuando un usuario arrastra una tarea, se envía el evento a todos los clientes del board
    @MessageMapping("/task/drag")
    public void handleTaskDrag(@Payload TaskDragEvent event) {
        logger.info("[WebSocket] Evento recibido en /task/drag: taskId={}, fromStatus={}, toStatus={}, boardId={}, userId={}",
                event.taskId, event.fromStatus, event.toStatus, event.boardId, event.userId);
        messagingTemplate.convertAndSend("/topic/task-drag." + event.boardId, event);
    }

    // Método para enviar el evento desde el backend (por ejemplo, al cambiar estado)
    public void sendTaskDragEvent(TaskDragEvent event, String boardId) {
        logger.info("[WebSocket] Enviando evento a /topic/task-drag.{}: taskId={}, fromStatus={}, toStatus={}, userId={}",
                boardId, event.taskId, event.fromStatus, event.toStatus, event.userId);
        messagingTemplate.convertAndSend("/topic/task-drag." + boardId, event);
    }

    // DTO para el evento de drag
    public static class TaskDragEvent {
        public String taskId;
        public String fromStatus;
        public String toStatus;
        public String boardId;
        public String userId;
        // Puedes agregar más campos si lo necesitas
    }
}
