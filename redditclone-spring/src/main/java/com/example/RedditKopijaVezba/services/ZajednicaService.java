package com.example.RedditKopijaVezba.services;

import com.example.RedditKopijaVezba.dto.ZajednicaDTO;
import com.example.RedditKopijaVezba.dto.ZajednicaSaveDTO;

import java.util.List;

public interface ZajednicaService {
    ZajednicaDTO save (ZajednicaSaveDTO zajednicaSaveDTO);
    ZajednicaDTO update (ZajednicaDTO zajednicaDTO);
    void deleteById (long id);
    List<ZajednicaDTO> getZajedniceNekogKorisnika(Long korisnikId);
    List<ZajednicaDTO> allZajednice();
}
