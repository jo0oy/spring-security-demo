package com.jo0oy.springsecuritydemo.note.web;

import com.jo0oy.springsecuritydemo.note.NoteService;
import com.jo0oy.springsecuritydemo.note.dto.NoteRegisterDto;
import com.jo0oy.springsecuritydemo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/note")
    public String getNote(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("notes", noteService.findByUser(user));

        return "note/index";
    }

    @PostMapping("/notes")
    public String registerNote(Authentication authentication, @ModelAttribute NoteRegisterDto request) {
        User user = (User) authentication.getPrincipal();
        noteService.register(user, request);

        return "redirect:note";
    }

    @DeleteMapping("/notes")
    public String deleteNote(Authentication authentication, @RequestParam Long id) {
        User user = (User) authentication.getPrincipal();
        noteService.delete(user, id);

        return "redirect:note";
    }
}
