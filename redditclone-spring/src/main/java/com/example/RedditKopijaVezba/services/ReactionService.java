package com.example.RedditKopijaVezba.services;

import com.example.RedditKopijaVezba.dto.ReactionDTO;
import com.example.RedditKopijaVezba.model.EReactionType;

public interface ReactionService {
    ReactionDTO vote (long postId, EReactionType reactionType);



}
