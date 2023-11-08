package com.jo0oy.springsecuritydemo.notice.web;

import com.jo0oy.springsecuritydemo.notice.NoticeService;
import com.jo0oy.springsecuritydemo.notice.dto.NoticeRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String notice(Model model) {

        model.addAttribute("notices", noticeService.findAll());

        return "notice/index";
    }

    @PostMapping("/notices")
    public String registerNotice(@ModelAttribute NoticeRegisterDto request) {

        noticeService.register(request);

        return "redirect:notice";
    }

    @DeleteMapping("/notices")
    public String deleteNotice(@RequestParam Long id) {

        noticeService.delete(id);

        return "redirect:notice";
    }
}
