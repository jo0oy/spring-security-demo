package com.jo0oy.springsecuritydemo.note;

import com.jo0oy.springsecuritydemo.global.exception.UserNotFoundException;
import com.jo0oy.springsecuritydemo.note.dto.NoteDto;
import com.jo0oy.springsecuritydemo.note.dto.NoteRegisterDto;
import com.jo0oy.springsecuritydemo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Transactional
    public NoteDto register(User user, NoteRegisterDto request) {
        if(Objects.isNull(user)) {
            throw new UserNotFoundException();
        }

        return new NoteDto(
            noteRepository.save(request.toEntity(user))
        );
    }

    @Transactional
    public void delete(User user, Long noteId) {
        if(Objects.isNull(user)) {
            throw new UserNotFoundException();
        }

        noteRepository.findByIdAndUser(noteId, user)
            .ifPresent(noteRepository::delete);
    }

    public List<NoteDto> findByUser(User user) {
        if(Objects.isNull(user)) {
            throw new UserNotFoundException();
        }

        if(user.isAdmin()) {
            return noteRepository.findAllWithUser("id", Sort.Direction.DESC.name())
                .stream()
                .map(NoteDto::new)
                .toList();
        }

        return noteRepository.findAllByUserOrderByIdDesc(user)
            .stream()
            .map(NoteDto::new)
            .toList();
    }
}
