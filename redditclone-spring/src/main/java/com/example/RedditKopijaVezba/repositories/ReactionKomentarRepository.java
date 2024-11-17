package com.example.RedditKopijaVezba.repositories;

import com.example.RedditKopijaVezba.model.EReactionType;
import com.example.RedditKopijaVezba.model.Reaction;
import com.example.RedditKopijaVezba.model.ReactionKomentar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionKomentarRepository extends JpaRepository<ReactionKomentar, Long> {
    int countByKomentar_IdAndTipReakcije(long postId, EReactionType type);
}
