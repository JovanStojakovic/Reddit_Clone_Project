package com.example.RedditKopijaVezba.model;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class ReactionKomentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private EReactionType tipReakcije;
    private LocalDate createReactionDate;
    @ManyToOne
    private Komentar komentar;
    @ManyToOne
    private Korisnik korisnik;

    public ReactionKomentar(EReactionType tipReakcije, Komentar komentar, Korisnik korisnik) {
        this.tipReakcije = tipReakcije;
        this.komentar = komentar;
        this.korisnik = korisnik;
    }

    public ReactionKomentar() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EReactionType getTipReakcije() {
        return tipReakcije;
    }

    public void setTipReakcije(EReactionType tipReakcije) {
        this.tipReakcije = tipReakcije;
    }

    public LocalDate getCreateReactionDate() {
        return createReactionDate;
    }

    public void setCreateReactionDate(LocalDate createReactionDate) {
        this.createReactionDate = createReactionDate;
    }

    public Komentar getKomentar() {
        return komentar;
    }

    public void setKomentar(Komentar komentar) {
        this.komentar = komentar;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
