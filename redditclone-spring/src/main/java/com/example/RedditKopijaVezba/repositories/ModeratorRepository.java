package com.example.RedditKopijaVezba.repositories;

import com.example.RedditKopijaVezba.dto.ZajednicaDTO;
import com.example.RedditKopijaVezba.model.Korisnik;
import com.example.RedditKopijaVezba.model.Moderator;
import com.example.RedditKopijaVezba.model.Zajednica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModeratorRepository extends JpaRepository<Moderator, Long> {

    // Primer za proveru da li postoji moderator za datog korisnika i datu zajednicu
    boolean existsByKorisnikAndZajednica(Korisnik korisnik, Zajednica zajednica);

    List<Moderator> findByKorisnikId(Long korisnikId);

    List<Moderator> findByZajednica(Zajednica zajednica);

}
