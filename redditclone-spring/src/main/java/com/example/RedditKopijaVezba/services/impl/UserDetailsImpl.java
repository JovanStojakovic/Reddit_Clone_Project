package com.example.RedditKopijaVezba.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.RedditKopijaVezba.dto.KorisnikDTO;
import com.example.RedditKopijaVezba.model.Korisnik;
import com.example.RedditKopijaVezba.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//OVaj servis nam sluzi za izvlaceje korisnickih detalja kada se ulogujemo
@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private KorisnikService korisnikService;



    @Autowired
    public UserDetailsImpl(KorisnikService korisnikService){
        this.korisnikService = korisnikService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        KorisnikDTO korisnikDTO = korisnikService.findByUsername(username);


        if(korisnikDTO == null){
            throw new UsernameNotFoundException("There is no user with username " + username);
        }else{
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            //ovde samo govorimo koja je rola
            String role = "ROLE_" + korisnikDTO.getRole();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));

            return new org.springframework.security.core.userdetails.User(
                    korisnikDTO.getUsername().trim(),
                    korisnikDTO.getPassword().trim(),
                    grantedAuthorities);
        }
    }

}

