package com.example.RedditKopijaVezba.repositories;

import com.example.RedditKopijaVezba.model.Komentar;
import com.example.RedditKopijaVezba.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KomentarRepository extends JpaRepository<Komentar, Long> {
    List<Komentar> findByPost_Id (long zajednicaId);
}
