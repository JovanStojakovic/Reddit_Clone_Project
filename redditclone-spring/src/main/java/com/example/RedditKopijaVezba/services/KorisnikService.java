package com.example.RedditKopijaVezba.services;

import com.example.RedditKopijaVezba.dto.IzmenaPodatakaKorisnikaDTO;
import com.example.RedditKopijaVezba.dto.KorisnikDTO;
import com.example.RedditKopijaVezba.dto.PromenaLozinkeDTO;
import com.example.RedditKopijaVezba.dto.RegistrationDTO;


import java.util.Optional;

public interface KorisnikService {
    //ovo je uzeto iz KorisnikRepozitory,
    //nakon sto ga ovde ubacim samo idem na KorisnikServiceImpl i tamo implementiram ovu metodu
    //samo stavljamo da nam sevisni sloj vraca DTO klase
    KorisnikDTO findByUsername(String username);

    //sad cemo da napravimo neku metodu za registraciju, koja ce da uzima podatke iz RegistrationDTO, i da sacuva to u bazu podataka
    //ono sto nam metoda vraca je KorisnikDTO - isto kao String, int samo je ovde povratna vrednost KorisnikDTO
    //ovde nemamo private, public, ptotected pa to znaci da je ovo dostupno samo u klasama unutar istog PAKETA
    KorisnikDTO register (RegistrationDTO registrationDTO);

    boolean promenaLozinke (String username, PromenaLozinkeDTO promenaLozinkeDTO);

    KorisnikDTO izmenaProfila (String username, IzmenaPodatakaKorisnikaDTO izmenaPodatakaKorisnikaDTO);


}
