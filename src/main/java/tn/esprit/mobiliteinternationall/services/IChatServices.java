package tn.esprit.mobiliteinternationall.services;

import tn.esprit.mobiliteinternationall.entites.Chat;
import tn.esprit.mobiliteinternationall.entites.Commentaire;

import java.util.List;

public interface IChatServices {
    public Chat addChat(Chat ch);
    public Chat updateChat(Chat ch);
    public Chat getChatById(Integer idChat);
    public List<Chat> getAllChat();
    public void removeChat(Integer idChat);
}
