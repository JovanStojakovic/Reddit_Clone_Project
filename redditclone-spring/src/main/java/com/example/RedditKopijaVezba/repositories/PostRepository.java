package com.example.RedditKopijaVezba.repositories;

import com.example.RedditKopijaVezba.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    //ovo je sad za sve postove iz jedne zajednice
    List<Post> findByZajednica_Id (long zajednicaId);

}
