package com.example.RedditKopijaVezba.security;

import javax.servlet.http.HttpServletRequest;

import com.example.RedditKopijaVezba.dto.KorisnikDTO;
import com.example.RedditKopijaVezba.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;



public class WebSecurity {

    @Autowired
    private KorisnikService korisnikService;

    public boolean checkClubId(Authentication authentication, HttpServletRequest request, int id) {
        //"UserDetails" je interfejs koji  koji predstavlja detalje o korisniku u okviru Spring Security modula.
        // Ovaj interfejs sadrži informacije o korisniku koje se koriste za autentifikaciju i autorizaciju u aplikaciji.
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        KorisnikDTO korisnikDTO = korisnikService.findByUsername(userDetails.getUsername());
        if(id == korisnikDTO.getId()) {
            return true;
        }
        return false;
    }

}
