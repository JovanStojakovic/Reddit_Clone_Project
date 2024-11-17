package com.example.RedditKopijaVezba.services.impl;


import com.example.RedditKopijaVezba.Izuzetak.NotFoundException;
import com.example.RedditKopijaVezba.dto.IzmenaPodatakaKorisnikaDTO;
import com.example.RedditKopijaVezba.dto.KorisnikDTO;
import com.example.RedditKopijaVezba.dto.PromenaLozinkeDTO;
import com.example.RedditKopijaVezba.dto.RegistrationDTO;
import com.example.RedditKopijaVezba.model.Administrator;
import com.example.RedditKopijaVezba.model.Korisnik;
import com.example.RedditKopijaVezba.model.Moderator;
import com.example.RedditKopijaVezba.repositories.KorisnikRepository;
import com.example.RedditKopijaVezba.services.KorisnikService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class KorisnikServiceImpl implements KorisnikService {
    //ovaj sloj koristi KorisnikRepository sloj
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    private ModelMapper modelMapper;
    //sad radimo hesiranje lozinke
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public KorisnikDTO findByUsername(String username) {

        //Korisnik korisnik ovo znaci da pravimo objekat korisnik koji ce da sadrzi u sebi ovo sto trazimo u nastavku
        //korisnikRepository.findByUsername(username).get() ovde pozivamo metodu iz KorisnikRepository
        //Ukratko, ova linija koda traži korisnika iz baze podataka na osnovu korisničkog imena
        // i čuva pronađenog korisnika u promenljivoj korisnik.
        Korisnik korisnik = korisnikRepository.findByUsername(username).get();

        //prebacujemo sad korisnik u korisnikDTO
        KorisnikDTO korisnikDTO = modelMapper.map(korisnik, KorisnikDTO.class);

        //sad rola treba da se dodeli, jer imamo 3 tipa korisnika
        //proveramamo da li je taj korisnik obican korisnik, ako jeste dodeli mi rolu Korisnik
        //(korisnik instanceof Korisnik) - ovako se proverava da li je korisnik instanca Korisnik
        if (korisnik instanceof Korisnik){
            korisnikDTO.setRole("KORISNIK");
        }
        //ako je Administrator dajemo mu rolu administrator
        else if (korisnik instanceof Administrator){
            korisnikDTO.setRole("ADMINISTRATOR");
        }
        //ako nije ni jedan od ta dva onda ovo
        else {
            korisnikDTO.setRole("MODERATOR");
        }

        return korisnikDTO;
    }

    //ovo je implementirana metoda iz KorisnikService
    //sad radimo nastavak na onu metodu i vracamo joj ono sto joj treba (KorisnikDTO)
    //znaci prco sve iz registrationDTO prebacujemo u Korisnik, pa onda pozivamo metodu da sacuva sve, pa onda iz toga u KorisnikDTO
    @Override
    public KorisnikDTO register(RegistrationDTO registrationDTO) {

        //sad treba da ono iz RegistrationDTO (ono sto smo trazili da se unuse u KorisnikService) treba da mapiramo(prebacimo) u Korisnik
        Korisnik korisnik = modelMapper.map(registrationDTO, Korisnik.class);

        //sad treba sa hesujemo lozinu
        korisnik.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        korisnik.setRegistrationDate(LocalDate.now());

        //sad hocemo da sacuvamo to, tako sto koristimo metodu iz korisnikRepository,sacuvamo sve u tabelu korisnik
        Korisnik saveKorisnik = korisnikRepository.save(korisnik);

        //sad ono iz objekta saveKorisnik mapiramo u KorisnikDTO jer nam je korisnikDTO povratna vrednost u KorisnikService
        return modelMapper.map(saveKorisnik, KorisnikDTO.class);
    }

    @Override
    public boolean promenaLozinke(String username, PromenaLozinkeDTO promenaLozinkeDTO) {
        Korisnik korisnik = korisnikRepository.findByUsername(username).get();
        String encodedNovaLozinka = passwordEncoder.encode(promenaLozinkeDTO.getNovaLozinka());
        korisnik.setPassword(encodedNovaLozinka);
        korisnikRepository.save(korisnik);
            return true;
        }

    @Override
    public KorisnikDTO izmenaProfila(String username, IzmenaPodatakaKorisnikaDTO izmenaPodatakaKorisnikaDTO) {
        Korisnik korisnik = korisnikRepository.findByUsername(username).get();
        korisnik.setDisplayName(izmenaPodatakaKorisnikaDTO.getDisplayName());
        korisnik.setDescription(izmenaPodatakaKorisnikaDTO.getDescription());
        Korisnik saveKorisnik = korisnikRepository.save(korisnik);
        return modelMapper.map(saveKorisnik, KorisnikDTO.class);
    }


}
