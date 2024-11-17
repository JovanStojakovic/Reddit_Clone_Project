package com.example.RedditKopijaVezba.services.impl;

import com.example.RedditKopijaVezba.Izuzetak.NotFoundException;
import com.example.RedditKopijaVezba.dto.ZajednicaDTO;
import com.example.RedditKopijaVezba.dto.ZajednicaSaveDTO;

import com.example.RedditKopijaVezba.model.Korisnik;
import com.example.RedditKopijaVezba.model.Moderator;
import com.example.RedditKopijaVezba.model.Post;
import com.example.RedditKopijaVezba.model.Zajednica;
import com.example.RedditKopijaVezba.repositories.KorisnikRepository;
import com.example.RedditKopijaVezba.repositories.ModeratorRepository;
import com.example.RedditKopijaVezba.repositories.ZajednicaRepository;
import com.example.RedditKopijaVezba.services.ZajednicaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZajednicaServiceImpl implements ZajednicaService {
    @Autowired
    private ZajednicaRepository zajednicaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private ModeratorRepository moderatorRepository;



    @Override
    public ZajednicaDTO save(ZajednicaSaveDTO zajednicaSaveDTO) {
        //proveravamo da li je korisnik ulogovan
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Izvlacimo trenutno ulogovanog korisnika
        String trenutnoUlogovan = auth.getName();


        // Pronalazimo korisnika iz baze podataka prema trenutnom korisničkom imenu
        Korisnik korisnik = korisnikRepository.findByUsername(trenutnoUlogovan)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen"));

        Zajednica zajednica = modelMapper.map(zajednicaSaveDTO, Zajednica.class);
        zajednica.setCreationDate(LocalDate.now());
        Zajednica saveZajednica = zajednicaRepository.save(zajednica);

        // Postavljanje trenutno ulogovanog korisnika kao moderatora
        Moderator moderator = new Moderator(korisnik, saveZajednica);
        moderatorRepository.save(moderator);

        return modelMapper.map(saveZajednica, ZajednicaDTO.class);

    }

    @Override
    public ZajednicaDTO update(ZajednicaDTO zajednicaDTO) {
        // Proveravamo da li je korisnik ulogovan
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String trenutnoUlogovan = auth.getName();

        // Pronalazimo trenutno ulogovanog korisnika
        Korisnik korisnik = korisnikRepository.findByUsername(trenutnoUlogovan)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen"));

        // Pronalazimo zajednicu koju želimo da izmenimo
        Zajednica trazenaZajednica = zajednicaRepository.findById(zajednicaDTO.getId())
                .orElseThrow(() -> new NotFoundException("Zajednica nije pronađena"));

        // Proveravamo da li je trenutno ulogovani korisnik moderator za datu zajednicu
        if (!moderatorRepository.existsByKorisnikAndZajednica(korisnik, trazenaZajednica)) {
            throw new AccessDeniedException("Nemate dozvolu za izmenu ove zajednice.");
        }

        // Postavljamo nove informacije na postojeću zajednicu
        trazenaZajednica.setName(zajednicaDTO.getName());
        trazenaZajednica.setDescription(zajednicaDTO.getDescription());

        // Čuvamo izmenjenu zajednicu u bazi podataka
        Zajednica saveZajednica = zajednicaRepository.save(trazenaZajednica);

        // Mapiranje rezultata na ZajednicaDTO i vraćanje
        return modelMapper.map(saveZajednica, ZajednicaDTO.class);
    }

    @Override
    public void deleteById(long id) {
        // Proveravamo da li je korisnik ulogovan
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String trenutnoUlogovan = auth.getName();

        // Pronalazimo trenutno ulogovanog korisnika
        Korisnik korisnik = korisnikRepository.findByUsername(trenutnoUlogovan)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen"));

        // Pronalazimo zajednicu koju želimo da obrišemo
        Zajednica trazenaZajednica = zajednicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Zajednica nije pronađena"));

        // Proveravamo da li je trenutno ulogovani korisnik moderator za datu zajednicu
        if (!moderatorRepository.existsByKorisnikAndZajednica(korisnik, trazenaZajednica)) {
            throw new AccessDeniedException("Nemate dozvolu za brisanje ove zajednice.");
        }

        // Pronalazimo sve redove iz tabele `moderator` koji referenciraju trenutnu zajednicu
        List<Moderator> moderatori = moderatorRepository.findByZajednica(trazenaZajednica);

        // Brišemo redove iz tabele `moderator`
        moderatorRepository.deleteAll(moderatori);

        // Brišemo red iz tabele `zajednica`
        zajednicaRepository.deleteById(id);
    }

    public List<ZajednicaDTO> getZajedniceNekogKorisnika(Long korisnikId) {
        List<Moderator> moderiraneZajednice = moderatorRepository.findByKorisnikId(korisnikId);
        List<ZajednicaDTO> zajedniceDTO = new ArrayList<>();

        for (Moderator moderator : moderiraneZajednice) {
            Zajednica zajednica = moderator.getZajednica();
            ZajednicaDTO zajednicaDTO = modelMapper.map(zajednica, ZajednicaDTO.class);
            zajedniceDTO.add(zajednicaDTO);
        }

        return zajedniceDTO;
    }

    @Override
    public List<ZajednicaDTO> allZajednice() {
        List<Zajednica> zajednice = zajednicaRepository.findAll();
        List<ZajednicaDTO> zajedniceDTO = new ArrayList<>();

        for (Zajednica zajednica : zajednice) {
            ZajednicaDTO zajednicaDTO = modelMapper.map(zajednica, ZajednicaDTO.class);
            zajedniceDTO.add(zajednicaDTO);
        }

        return zajedniceDTO;
    }

}
