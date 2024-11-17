package com.example.RedditKopijaVezba.dto;

public class TokenDTO {
    private String token;

    //da bi token dobijali kao odgovor pozzivajuci druge metode OBAVEZNO je da se ovde napravi konstruktor
    public TokenDTO(String token) {
        this.token = token;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
