package com.example.RedditKopijaVezba.controller;



import com.example.RedditKopijaVezba.dto.PostDTO;
import com.example.RedditKopijaVezba.dto.ReactionDTO;
import com.example.RedditKopijaVezba.dto.ZajednicaDTO;
import com.example.RedditKopijaVezba.model.EReactionType;
import com.example.RedditKopijaVezba.services.PostService;
import com.example.RedditKopijaVezba.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ReactionService reactionService;


    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> allPosts() {
        List<PostDTO> postDTO = postService.allPosts();
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<PostDTO> addPost( @RequestBody PostDTO postDTO) {
        PostDTO savePostDTO = postService.save(postDTO);
        return new ResponseEntity<>(savePostDTO, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDTO> updatePostById(@PathVariable long id, @RequestBody PostDTO postDTO) {
    postDTO.setId(id);
        PostDTO izmenaPostDTO = postService.update(postDTO);
        return ResponseEntity.ok(izmenaPostDTO);
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteByIdPost (@PathVariable long id){
        postService.deleteByIdPost(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("{id}/upvote")
    public ResponseEntity<ReactionDTO> upvote (@PathVariable long id){
        return new ResponseEntity<>(reactionService.vote(id, EReactionType.UPVOTE), HttpStatus.CREATED);
    }
    @PostMapping("{id}/downvote")
    public ResponseEntity<ReactionDTO> downvote (@PathVariable long id){
        return new ResponseEntity<>(reactionService.vote(id, EReactionType.DOWNVOTE), HttpStatus.CREATED);
    }

}
