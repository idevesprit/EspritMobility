package tn.esprit.mobiliteinternationall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.mobiliteinternationall.entites.Chat;
import tn.esprit.mobiliteinternationall.repositories.ChatRepository;
import tn.esprit.mobiliteinternationall.repositories.CommentaireRepository;

import java.util.List;

@Service
public class ChatServices implements IChatServices{

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat addChat(Chat ch) {
        return chatRepository.save(ch);
    }

    @Override
    public Chat updateChat(Chat ch) {
        return chatRepository.save(ch);
    }

    @Override
    public Chat getChatById(Integer idChat) {
        return chatRepository.findById(idChat).orElse(null);
    }

    @Override
    public List<Chat> getAllChat() {
        return chatRepository.findAll();
    }

    @Override
    public void removeChat(Integer idChat) {
        chatRepository.deleteById(idChat);
    }
}
