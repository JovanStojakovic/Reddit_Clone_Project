package com.example.RedditKopijaVezba.repositories;

import com.example.RedditKopijaVezba.model.EReactionType;
import com.example.RedditKopijaVezba.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    int countByPost_IdAndTipReakcije(long postId, EReactionType type);
}
