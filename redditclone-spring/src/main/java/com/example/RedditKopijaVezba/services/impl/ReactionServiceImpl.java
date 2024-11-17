package com.example.RedditKopijaVezba.services.impl;

import com.example.RedditKopijaVezba.dto.ReactionDTO;
import com.example.RedditKopijaVezba.model.EReactionType;
import com.example.RedditKopijaVezba.model.Korisnik;
import com.example.RedditKopijaVezba.model.Post;
import com.example.RedditKopijaVezba.model.Reaction;
import com.example.RedditKopijaVezba.repositories.KorisnikRepository;
import com.example.RedditKopijaVezba.repositories.PostRepository;
import com.example.RedditKopijaVezba.repositories.ReactionRepository;
import com.example.RedditKopijaVezba.services.ReactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImpl implements ReactionService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReactionDTO vote(long postId, EReactionType reactionType) {
        Post post = postRepository.findById(postId).get();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Korisnik korisnik = korisnikRepository.findByUsername(userDetails.getUsername()).get();

        //pravi se reakcije i upisuje u bazu u tabelu reaction
        Reaction reaction = new Reaction(reactionType, post, korisnik);
        Reaction savedReaction = reactionRepository.save(reaction);

        return modelMapper.map(savedReaction, ReactionDTO.class);
    }


}
