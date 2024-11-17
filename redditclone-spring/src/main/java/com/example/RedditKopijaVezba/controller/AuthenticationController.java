package com.example.RedditKopijaVezba.controller;

import com.example.RedditKopijaVezba.dto.*;
import com.example.RedditKopijaVezba.security.TokenUtils;
import com.example.RedditKopijaVezba.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;

    @PostMapping("/registration")
    //ResponseEntity je klasa u Spring Framework-u koja predstavlja HTTP odgovor koji se šalje nazad klijentu.
    // Ova klasa omogućava kontroliranje svih aspekata HTTP odgovora, uključujući statusni kod, zaglavlja i telo odgovora.

    //pravi se metoda koja ima tip povrate vrednosti ResponseEntity... umesto String, int, void nama je ovo
    //tako da metoda koja se zove registration trazi povratnu vrednost ResponseEntity koja sadrzi podatke iz <KorisnikDTO>

    //ovde zelimo da nam kao odgovor vrati sve sto je navedeno u KorisnikDTO
    //@RequestBody RegistrationDTO registrationDTO - znaci da sve sto je uneto u bodyu postmana da je to ustari RegistrationDTO(stavri koje se u njemu traze)

    private ResponseEntity<KorisnikDTO> registration (@RequestBody RegistrationDTO registrationDTO){
        //pravimo objekat klase KorisnikDTO koji u sebi sadrzi sve iz te klase i pozivamo metodu iz KorisnikSevice
        KorisnikDTO korisnikDTO = korisnikService.register(registrationDTO);
        //vrati mi novog korisnikDTO
        return new ResponseEntity<>(korisnikDTO, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<TokenDTO> createAuthenticationToken(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {

        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),loginDTO.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        //UserDetails to je interface koji vec postoji u sistemu i on sadrzi podatke o korisniku
        //za njega samo napravimo UserDetailsImpl
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(userDetails);
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new TokenDTO(jwt));
    }

    @PostMapping("/promenaLozinke/{username}")
    public  ResponseEntity promenaLozinke (@PathVariable("username") String username, @RequestBody PromenaLozinkeDTO promenaLozinkeDTO){
        KorisnikDTO korisnikDTO = korisnikService.findByUsername(username);
        Boolean bul = korisnikService.promenaLozinke(username, promenaLozinkeDTO);
        return new ResponseEntity(bul, HttpStatus.OK);
    }
    @PostMapping("/izmenaProfila/{username}")
    public  ResponseEntity promenaLozinke (@PathVariable("username") String username, @RequestBody IzmenaPodatakaKorisnikaDTO izmenaPodatakaKorisnikaDTO){
        KorisnikDTO korisnikDTO1 = korisnikService.findByUsername(username);
        KorisnikDTO korisnikDTO = korisnikService.izmenaProfila(korisnikDTO1.getUsername(), izmenaPodatakaKorisnikaDTO);
        return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
    }

}
