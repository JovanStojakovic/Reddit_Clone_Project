package com.example.RedditKopijaVezba.controller;

import com.example.RedditKopijaVezba.dto.PostDTO;
import com.example.RedditKopijaVezba.dto.ZajednicaDTO;
import com.example.RedditKopijaVezba.dto.ZajednicaSaveDTO;
import com.example.RedditKopijaVezba.services.PostService;
import com.example.RedditKopijaVezba.services.ZajednicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zajednice")
public class ZajednicaController {

    @Autowired
    private ZajednicaService zajednicaService;
    @Autowired
    private PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<ZajednicaDTO>> allZajednice() {
        List<ZajednicaDTO> zajedniceDTO = zajednicaService.allZajednice();
        return new ResponseEntity<>(zajedniceDTO, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ZajednicaDTO> addZajednica (@RequestBody ZajednicaSaveDTO zajednicaDTO){
        ZajednicaDTO saveZajednicaDTO = zajednicaService.save(zajednicaDTO);
        return new ResponseEntity<>(saveZajednicaDTO, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    ResponseEntity<ZajednicaDTO> updateZajednicaById (@PathVariable long id, @RequestBody ZajednicaDTO zajednicaDTO){
        zajednicaDTO.setId(id);
        ZajednicaDTO updateZajednicaDTO = zajednicaService.update(zajednicaDTO);
        return new ResponseEntity<>(updateZajednicaDTO, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteByIdZajednica (@PathVariable long id){
        zajednicaService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/korisnik/{id}")
    public ResponseEntity<List<ZajednicaDTO>> getZajedniceNekogKorisnika(@PathVariable long id) {
        List<ZajednicaDTO> zajedniceDTO = zajednicaService.getZajedniceNekogKorisnika(id);
        return new ResponseEntity<>(zajedniceDTO, HttpStatus.OK);
    }

    @GetMapping("{id}/posts")
    public ResponseEntity<List<PostDTO>> getPostsJedneZajednice(@PathVariable long id) {
        List<PostDTO> postsDTO = postService.findByZajednica_Id(id);
        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }
}
