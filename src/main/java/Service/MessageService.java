package Service;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }
    public Message createMessage(Message message) {
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty()) {
            return null;
        }
        if (message.getMessage_text().length() >= 255) {
            return null;
        }
        if (accountDAO.getAccountById(message.getPosted_by()) == null) {
            return null;
        }

        return messageDAO.createMessage(message);
    }
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    public Message deleteMessageById(int id) {
        return messageDAO.deleteMessageById(id);
    }

    public Message updateMessage(int id, String newText) {
        if (newText == null || newText.trim().isEmpty() || newText.length() >= 255) {
            return null;
        }
        return messageDAO.updateMessageText(id, newText);
    }

    public List<Message> getAllMessagesByAccountId(int accountId) {
        return messageDAO.getMessagesByAccountId(accountId);
    }
}
