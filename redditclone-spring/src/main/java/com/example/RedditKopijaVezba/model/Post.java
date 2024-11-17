package com.example.RedditKopijaVezba.model;

import javax.persistence.*;


import java.time.LocalDate;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String text;
    private LocalDate creationDate;
    private String imagePath;
    @ManyToOne
    private Korisnik korisnik;
    @ManyToOne
    private Zajednica zajednica;

    //jedan prema vise, jedan post moze da ima vise flaira
    @ManyToOne
    private Flair flair;





    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Zajednica getZajednica() {
        return zajednica;
    }

    public void setZajednica(Zajednica zajednica) {
        this.zajednica = zajednica;
    }

    public Flair getFlair() {
        return flair;
    }

    public void setFlair(Flair flair) {
        this.flair = flair;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnikKojiJePostavio) {
        this.korisnik = korisnikKojiJePostavio;
    }
}
