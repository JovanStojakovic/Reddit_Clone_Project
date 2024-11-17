package com.example.RedditKopijaVezba.services;

import com.example.RedditKopijaVezba.dto.KomentarDTO;
import com.example.RedditKopijaVezba.dto.PostDTO;

import java.util.List;

public interface KomentarService {
    KomentarDTO save (KomentarDTO komentarDTO);
    KomentarDTO update (KomentarDTO komentarDTO);
    void deleteByIdPost (long id);
    List<KomentarDTO> findByPost_Id (long postId);

    int karma(long komentarId);
}
