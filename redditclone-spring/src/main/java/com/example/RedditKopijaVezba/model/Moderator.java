//Moderator ce biti posebna tabela u kojoj ce da bude taj korisnik i zajednica kojoj je moderator



package com.example.RedditKopijaVezba.model;

import javax.persistence.*;

@Entity
public class Moderator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Korisnik korisnik;

    @ManyToOne
    private Zajednica zajednica;



    public Moderator() {

    }
    public Moderator(Korisnik korisnik, Zajednica zajednica) {
        this.korisnik = korisnik;
        this.zajednica = zajednica;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Zajednica getZajednica() {
        return zajednica;
    }

    public void setZajednica(Zajednica zajednica) {
        this.zajednica = zajednica;
    }
}
