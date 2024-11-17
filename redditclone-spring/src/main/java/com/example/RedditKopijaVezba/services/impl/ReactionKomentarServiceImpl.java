package com.example.RedditKopijaVezba.services.impl;

import com.example.RedditKopijaVezba.dto.ReactionDTO;
import com.example.RedditKopijaVezba.dto.ReactionKomentarDTO;
import com.example.RedditKopijaVezba.model.*;
import com.example.RedditKopijaVezba.repositories.KomentarRepository;
import com.example.RedditKopijaVezba.repositories.KorisnikRepository;
import com.example.RedditKopijaVezba.repositories.ReactionKomentarRepository;
import com.example.RedditKopijaVezba.services.ReactionKomentarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ReactionKomentarServiceImpl implements ReactionKomentarService {
    @Autowired
    KomentarRepository komentarRepository;
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    ReactionKomentarRepository reactionKomentarRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ReactionKomentarDTO vote(long komentarId, EReactionType tipReakcije) {
        Komentar komentar = komentarRepository.findById(komentarId).get();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Korisnik korisnik = korisnikRepository.findByUsername(userDetails.getUsername()).get();

        //pravi se reakcije i upisuje u bazu u tabelu reaction
        ReactionKomentar reactionKomentar = new ReactionKomentar(tipReakcije, komentar, korisnik);
        ReactionKomentar savedReactionKomentar = reactionKomentarRepository.save(reactionKomentar);

        return modelMapper.map(savedReactionKomentar, ReactionKomentarDTO.class);
    }
}
