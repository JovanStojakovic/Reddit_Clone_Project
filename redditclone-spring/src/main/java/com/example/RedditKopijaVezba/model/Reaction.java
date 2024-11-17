package com.example.RedditKopijaVezba.model;

import javax.persistence.*;


import java.time.LocalDate;

@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private EReactionType tipReakcije;
    private LocalDate createReactionDate;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Korisnik korisnik;


    public Reaction(EReactionType tipReakcije, Post post, Korisnik korisnik) {
        this.tipReakcije = tipReakcije;
        this.post = post;
        this.korisnik = korisnik;
    }

    public Reaction() {
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
