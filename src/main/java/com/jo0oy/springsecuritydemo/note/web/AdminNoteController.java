package com.jo0oy.springsecuritydemo.note.web;


import com.jo0oy.springsecuritydemo.note.NoteService;
import com.jo0oy.springsecuritydemo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/note")
@Controller
public class AdminNoteController {

    private final NoteService noteService;

    @GetMapping("/admin")
    public String adminNote(Authentication authentication, Model model) {

        model.addAttribute("notes",
            noteService.findByUser((User) authentication.getPrincipal()));

        return "note/admin";
    }
}
