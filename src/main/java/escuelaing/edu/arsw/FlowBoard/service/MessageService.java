package escuelaing.edu.arsw.FlowBoard.service;

import escuelaing.edu.arsw.FlowBoard.model.Message;
import escuelaing.edu.arsw.FlowBoard.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByTaskId(String taskId) {
        return messageRepository.findByTaskId(taskId);
    }

    public Optional<Message> getMessageById(String id) {
        return messageRepository.findById(id);
    }

    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }
}
