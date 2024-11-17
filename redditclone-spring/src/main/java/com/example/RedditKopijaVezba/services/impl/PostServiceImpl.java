package com.example.RedditKopijaVezba.services.impl;

import com.example.RedditKopijaVezba.Izuzetak.NotFoundException;
import com.example.RedditKopijaVezba.ModelMapperConvertor.ZajednicaToLongConverter;
import com.example.RedditKopijaVezba.ModelMapperConvertor.KorisnikToLongConvertor;


import com.example.RedditKopijaVezba.dto.PostDTO;
import com.example.RedditKopijaVezba.dto.ZajednicaDTO;
import com.example.RedditKopijaVezba.model.*;
import com.example.RedditKopijaVezba.repositories.*;
import com.example.RedditKopijaVezba.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private ZajednicaRepository zajednicaRepository;
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private FlairRepository flairRepository;
    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureModelMapper() {
        modelMapper.addConverter(new ZajednicaToLongConverter());
        modelMapper.addConverter(new KorisnikToLongConvertor());
    }

    @Override
    public PostDTO save(PostDTO postDTO) {
        // Proveravamo da li je korisnik ulogovan
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Izvlačimo trenutno ulogovanog korisnika
        String trenutnoUlogovan = auth.getName();

        // Pronalazimo korisnika iz baze podataka prema trenutnom korisničkom imenu
        Korisnik korisnik = korisnikRepository.findByUsername(trenutnoUlogovan)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen"));

        // Dobijamo zajednicu
        Zajednica zajednica = zajednicaRepository.findById(postDTO.getZajednica())
                .orElseThrow(() -> new NotFoundException("Zajednica nije pronađena"));

        // Ovo dole je logika koju treba zapamtiti, slična logici za kreiranje zajednice
        Post post = modelMapper.map(postDTO, Post.class);
        // Dodajemo trenutno ulogovanog korisnika kao autora posta
        post.setKorisnik(korisnik);
        post.setCreationDate(LocalDate.now());
        post.setZajednica(zajednica);
        // Čuvamo post u bazi
        Post savePost = postRepository.save(post);
        // Mapiramo u PostDTO i to vraćamo
        return modelMapper.map(savePost, PostDTO.class);
    }

    @Override
    public void deleteByIdPost(long id) {
        // Proveravamo da li je korisnik ulogovan
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Izvlačimo trenutno ulogovanog korisnika
        String trenutnoUlogovan = auth.getName();

        // Dobijamo post iz baze prema ID-ju
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post nije pronađen"));

        // Proveravamo da li trenutno ulogovani korisnik ima dozvolu za brisanje posta
        if (!trenutnoUlogovan.equals(post.getKorisnik().getUsername())) {
            throw new AccessDeniedException("Nemate dozvolu za brisanje ovog posta.");
        }
        postRepository.deleteById(id);
    }

    @Override
    public PostDTO update(PostDTO postDTO) {
        // Proveravamo da li je korisnik ulogovan
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Izvlačimo trenutno ulogovanog korisnika
        String trenutnoUlogovan = auth.getName();

        // Pronalazimo korisnika iz baze podataka prema trenutnom korisničkom imenu
        Korisnik korisnik = korisnikRepository.findByUsername(trenutnoUlogovan)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen"));

        // Dobijamo post iz baze prema ID-ju iz DTO objekta
        Post post = postRepository.findById(postDTO.getId())
                .orElseThrow(() -> new NotFoundException("Post nije pronađen"));


        // Proveravamo da li trenutno ulogovani korisnik ima dozvolu za izmenu posta
        if (korisnik.getId() != post.getKorisnik().getId()) {
            throw new AccessDeniedException("Nemate dozvolu za izmenu ovog posta.");
        }

        // Proveravamo da li trenutno ulogovani korisnik ima dozvolu za izmenu posta
        /*if (!korisnik.equals(post.getKorisnik())) {
            throw new AccessDeniedException("Nemate dozvolu za izmenu ovog posta.");
        }*/

        // Postavljanje novih vrednosti
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());

        // Čuvamo izmenjen post u bazi
        Post savePost = postRepository.save(post);

        // Mapiramo u PostDTO i to vraćamo
        return modelMapper.map(savePost, PostDTO.class);
    }
    @Override
    public List<PostDTO> allPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (Post post : posts) {
            PostDTO postDTO = modelMapper.map(post, PostDTO.class);
            postDTO.setUserId(post.getKorisnik().getId());
            postDTO.setKarma(karma(post.getId()));
            postDTOList.add(postDTO);
        }

        return postDTOList;
    }




    @Override
    public List<PostDTO> findByZajednica_Id(long zajednicaId) {
        //ovo pisemo da pronadjemo sve postove koji pripadaju datoj zajednici i upisemo ih u listu posts
        List<Post> posts = postRepository.findByZajednica_Id(zajednicaId);
        //kreira se nova prazna lista postsDTO
        List<PostDTO> postsDTO = new ArrayList<>();
        //prolazimo kroz savki Post objekat u listi posts, i mapiramo svaki Post objekat u odgovarajuci PostDTO objekat
        //setujemo karmu
        for (Post post : posts){
            PostDTO postDTO = modelMapper.map(post, PostDTO.class);
            postDTO.setUserId(post.getKorisnik().getId());
            postDTO.setKarma(karma(postDTO.getId()));
            postsDTO.add(postDTO);

        }
        //na kraju se ta lista postsDTO sa mapiranim objektima vraca kao rezultat metode
        return postsDTO;
    }

    @Override
    public int karma(long postId) {
        //karma predstavlja broj UPVOTE -  Broj DOWNVOTE
        return reactionRepository.countByPost_IdAndTipReakcije(postId, EReactionType.UPVOTE) - reactionRepository.countByPost_IdAndTipReakcije(postId, EReactionType.DOWNVOTE);
    }
}
