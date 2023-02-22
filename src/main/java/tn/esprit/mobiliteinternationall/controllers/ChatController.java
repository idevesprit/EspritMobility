package tn.esprit.mobiliteinternationall.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mobiliteinternationall.entites.Chat;
import tn.esprit.mobiliteinternationall.services.IChatServices;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/chatt")
public class ChatController {

    @Autowired
    IChatServices iChatServices;

    @PostMapping("/addchat")
    public Chat addChat(@RequestBody Chat ch){
        return iChatServices.addChat(ch);}

    @PutMapping("/updatechat")
    public Chat updateChat(@RequestBody Chat ch) {
        return iChatServices.updateChat(ch);}

    @GetMapping("/chatbyid/{idChat}")
    public Chat FindChatById(@PathVariable int idChat) {
        return iChatServices.getChatById(idChat);}

    @GetMapping("/allchat")
    public List<Chat> FindAllChat() {
        return iChatServices.getAllChat();}

    @DeleteMapping("/remove/{idChat}")
    public void removeChat(@PathVariable int idChat){
        iChatServices.removeChat(idChat);}

}
