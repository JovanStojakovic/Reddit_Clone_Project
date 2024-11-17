package com.example.RedditKopijaVezba.services;

import com.example.RedditKopijaVezba.dto.PostDTO;
import com.example.RedditKopijaVezba.dto.ZajednicaDTO;
import com.example.RedditKopijaVezba.model.Post;

import java.util.List;

public interface PostService {
    List<PostDTO> allPosts();

    PostDTO save (PostDTO postDTO);
    PostDTO update (PostDTO postDTO);
    void deleteByIdPost (long id);
    //prikaz postova jedne zajednice
    List<PostDTO> findByZajednica_Id (long zajednicaId);
    int karma (long postId);

}
