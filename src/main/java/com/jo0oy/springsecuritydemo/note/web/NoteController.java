package com.jo0oy.springsecuritydemo.note.web;

import com.jo0oy.springsecuritydemo.note.NoteService;
import com.jo0oy.springsecuritydemo.note.dto.NoteRegisterDto;
import com.jo0oy.springsecuritydemo.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/note")
    public String getNote(@AuthenticationPrincipal AuthUser authUser,
                          Model model) {

        model.addAttribute("notes",
            noteService.findByUser(authUser.getUser()));

        return "note/index";
    }

    @PostMapping("/notes")
    public String registerNote(@AuthenticationPrincipal AuthUser authUser,
                               @ModelAttribute NoteRegisterDto request) {
        noteService.register(authUser.getUser(), request);

        return "redirect:note";
    }

    @DeleteMapping("/notes")
    public String deleteNote(@AuthenticationPrincipal AuthUser authUser,
                             @RequestParam Long id) {

        noteService.delete(authUser.getUser(), id);

        return "redirect:note";
    }
}
