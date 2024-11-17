package com.example.RedditKopijaVezba.controller;

import com.example.RedditKopijaVezba.dto.KomentarDTO;
import com.example.RedditKopijaVezba.dto.PostDTO;
import com.example.RedditKopijaVezba.dto.ReactionDTO;
import com.example.RedditKopijaVezba.dto.ReactionKomentarDTO;
import com.example.RedditKopijaVezba.model.EReactionType;
import com.example.RedditKopijaVezba.model.ReactionKomentar;
import com.example.RedditKopijaVezba.services.KomentarService;
import com.example.RedditKopijaVezba.services.ReactionKomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/komentar")
public class KomentarController {
    @Autowired
    KomentarService komentarService;
    @Autowired
    ReactionKomentarService reactionKomentarService;

    @PostMapping
    public ResponseEntity<KomentarDTO> addKomentar(@RequestBody KomentarDTO komentarDTO) {
        KomentarDTO saveKomentarDTO = komentarService.save(komentarDTO);
        return new ResponseEntity<>(saveKomentarDTO, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<KomentarDTO> updateKomentarById(@PathVariable long id, @RequestBody KomentarDTO komentarDTO) {
        komentarDTO.setId(id);
        KomentarDTO izmenaKomentaraDTO = komentarService.update(komentarDTO);
        return ResponseEntity.ok(izmenaKomentaraDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteByIdPost (@PathVariable long id){
        komentarService.deleteByIdPost(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<KomentarDTO>> getKomentariJednogPosta(@PathVariable long id) {
        List<KomentarDTO> komentarDTO = komentarService.findByPost_Id(id);
        return new ResponseEntity<>(komentarDTO, HttpStatus.OK);
    }

    @PostMapping("{id}/upvote")
    public ResponseEntity<ReactionKomentarDTO> upvoteKomentar (@PathVariable long id){
        return new ResponseEntity<>(reactionKomentarService.vote(id, EReactionType.UPVOTE), HttpStatus.CREATED);
    }
    @PostMapping("{id}/downvote")
    public ResponseEntity<ReactionKomentarDTO> downvoteKomentar (@PathVariable long id){
        return new ResponseEntity<>(reactionKomentarService.vote(id, EReactionType.DOWNVOTE), HttpStatus.CREATED);
    }
}
