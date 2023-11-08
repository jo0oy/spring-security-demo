package com.jo0oy.springsecuritydemo.note;

import com.jo0oy.springsecuritydemo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note n join fetch n.user order by n.id desc")
    List<Note> findAllWithUserOrderByIdDesc();

    List<Note> findAllByUserOrderByIdDesc(User user);

    Optional<Note> findByIdAndUser(Long id, User user);
}
