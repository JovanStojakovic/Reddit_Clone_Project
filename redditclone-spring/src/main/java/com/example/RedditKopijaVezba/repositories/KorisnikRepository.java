package com.example.RedditKopijaVezba.repositories;

import com.example.RedditKopijaVezba.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    //trebace nam metoda za pretragu korisnika po username pa hajde da je napravimo
    //na primer kad se neko bude prijavio, preko username cemo da nadjemo da li postoji taj username
    //Optional<Korisnik> - koristimo jer ako postoji korisnik vraca nam ga, a ako ne postoji vraca nam prazan Optional
    Optional<Korisnik> findByUsername(String username);


}
