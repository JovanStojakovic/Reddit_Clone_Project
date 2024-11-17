package com.example.RedditKopijaVezba.services.impl;

import com.example.RedditKopijaVezba.Izuzetak.NotFoundException;
import com.example.RedditKopijaVezba.ModelMapperConvertor.KorisnikToLongConvertor;
import com.example.RedditKopijaVezba.ModelMapperConvertor.ZajednicaToLongConverter;
import com.example.RedditKopijaVezba.dto.KomentarDTO;
import com.example.RedditKopijaVezba.model.*;
import com.example.RedditKopijaVezba.repositories.KomentarRepository;
import com.example.RedditKopijaVezba.repositories.KorisnikRepository;
import com.example.RedditKopijaVezba.repositories.PostRepository;
import com.example.RedditKopijaVezba.repositories.ReactionKomentarRepository;
import com.example.RedditKopijaVezba.services.KomentarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class KomentarServiceImpl implements KomentarService {
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private KomentarRepository komentarRepository;
    @Autowired
    private ReactionKomentarRepository reactionKomentarRepository;

    @Autowired
    private ModelMapper modelMapper;
    @PostConstruct
    public void configureModelMapper() {
        modelMapper.addConverter(new ZajednicaToLongConverter());
        modelMapper.addConverter(new KorisnikToLongConvertor());
    }



    @Override
    public KomentarDTO save(KomentarDTO komentarDTO) {
        // Proveravamo da li je korisnik ulogovan
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Izvlačimo trenutno ulogovanog korisnika
        String trenutnoUlogovan = auth.getName();

        // Pronalazimo korisnika iz baze podataka prema trenutnom korisničkom imenu
        Korisnik korisnik = korisnikRepository.findByUsername(trenutnoUlogovan)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen"));
        // Dobijamo post
        Post post = postRepository.findById(komentarDTO.getPostId())
                .orElseThrow(() -> new NotFoundException("Post nije pronađen"));
        Komentar komentar = modelMapper.map(komentarDTO, Komentar.class);

        komentar.setKorisnik(korisnik);
        komentar.setCreationDate(LocalDate.now());
        komentar.setPost(post);
        // Čuvamo post u bazi
        Komentar saveKomentar = komentarRepository.save(komentar);
        // Mapiramo u PostDTO i to vraćamo
        return modelMapper.map(saveKomentar, KomentarDTO.class);
    }

    @Override
    public KomentarDTO update(KomentarDTO komentarDTO) {
        // Proveravamo da li je korisnik ulogovan
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Izvlačimo trenutno ulogovanog korisnika
        String trenutnoUlogovan = auth.getName();

        // Pronalazimo korisnika iz baze podataka prema trenutnom korisničkom imenu
        Korisnik korisnik = korisnikRepository.findByUsername(trenutnoUlogovan)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen"));

        // Dobijamo komentar iz baze prema ID-ju iz DTO objekta
        Komentar komentar = komentarRepository.findById(komentarDTO.getId())
                .orElseThrow(() -> new NotFoundException("Komentar nije pronađen"));

        // Proveravamo da li trenutno ulogovani korisnik ima dozvolu za izmenu komentara
        if (korisnik.getId() != komentar.getKorisnik().getId()) {
            throw new AccessDeniedException("Nemate dozvolu za izmenu ovog posta.");
        }

        // Postavljanje novih vrednosti iz komentarDTO u komentar objekat
        komentar.setText(komentarDTO.getText());

        // Čuvamo izmenjen komentar u bazi
        Komentar saveKomentar = komentarRepository.save(komentar);

        // Mapiramo u KomentarDTO i to vraćamo
        return modelMapper.map(saveKomentar, KomentarDTO.class);
    }

    @Override
    public void deleteByIdPost(long id) {
        // Proveravamo da li je korisnik ulogovan
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Izvlačimo trenutno ulogovanog korisnika
        String trenutnoUlogovan = auth.getName();

        // Dobijamo komentar iz baze prema ID-ju
        Komentar komentar = komentarRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Komentar nije pronađen"));

        // Proveravamo da li trenutno ulogovani korisnik ima dozvolu za brisanje komentara
        if (!trenutnoUlogovan.equals(komentar.getKorisnik().getUsername())) {
            throw new AccessDeniedException("Nemate dozvolu za brisanje ovog komentara.");
        }
        komentarRepository.deleteById(id);
    }

    @Override
    public List<KomentarDTO> findByPost_Id(long postId) {
        //ovo pisemo da pronadjemo sve komentare koji pripadaju datom postu i upisemo ih u listu komentari
        List<Komentar> komentari = komentarRepository.findByPost_Id(postId);
        //kreira se nova prazna lista komenatriDTO
        List<KomentarDTO> komentariDTO = new ArrayList<>();
        //prolazimo kroz savki Komentar objekat u listi komentari, i mapiramo svaki Komentar objekat u odgovarajuci KomentarDTO objekat
        //setujemo karmu
        for (Komentar komentar : komentari){
            KomentarDTO komentarDTO = modelMapper.map(komentar, KomentarDTO.class);
            komentarDTO.setKarma(karma(komentarDTO.getId()));
            komentariDTO.add(komentarDTO);

        }
        //na kraju se ta lista postsDTO sa mapiranim objektima vraca kao rezultat metode
        return komentariDTO;
    }
    @Override
    public int karma(long komentarId) {
        //karma predstavlja broj UPVOTE -  Broj DOWNVOTE
        return reactionKomentarRepository.countByKomentar_IdAndTipReakcije(komentarId, EReactionType.UPVOTE) - reactionKomentarRepository.countByKomentar_IdAndTipReakcije(komentarId, EReactionType.DOWNVOTE);
    }

}
