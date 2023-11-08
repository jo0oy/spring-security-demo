package com.jo0oy.springsecuritydemo.notice;

import com.jo0oy.springsecuritydemo.notice.dto.NoticeDto;
import com.jo0oy.springsecuritydemo.notice.dto.NoticeRegisterDto;
import com.jo0oy.springsecuritydemo.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public NoticeDto register(NoticeRegisterDto request) {
        log.info("{} ::: {}", getClass().getSimpleName(), "register");

        return new NoticeDto(
            noticeRepository.save(request.toEntity())
        );
    }

    @Transactional
    public void delete(Long noticeId) {
        log.info("{} ::: {}", getClass().getSimpleName(), "delete");

        noticeRepository.findById(noticeId)
            .ifPresent(noticeRepository::delete);
    }

    public List<NoticeDto> findAll() {
        log.info("{} ::: {}", getClass().getSimpleName(), "findAll");

        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
            .stream()
            .map(NoticeDto::new)
            .toList();
    }
}
