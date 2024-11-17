package com.example.RedditKopijaVezba.services;

import com.example.RedditKopijaVezba.dto.ReactionDTO;
import com.example.RedditKopijaVezba.dto.ReactionKomentarDTO;
import com.example.RedditKopijaVezba.model.EReactionType;

public interface ReactionKomentarService {
    ReactionKomentarDTO vote (long postId, EReactionType reactionType);
}
