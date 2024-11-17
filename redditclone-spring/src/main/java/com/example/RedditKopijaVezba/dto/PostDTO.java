package com.example.RedditKopijaVezba.dto;


import java.time.LocalDate;

public class PostDTO {
    private long id;

    private String title;
    private String text;
    private LocalDate creationDate;
    private String imagePath;
   //stavljamo umesto Zajednica i Flair, umesto njih ide Long zato sto nam trebaju samo njihovi ID
    private long zajednica;
    //private long flair;
    private long userId;
    //karma se ne cuva u bazi pa je samo ovde pisemo
    private int karma;


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

    public long getZajednica() {
        return zajednica;
    }

    public void setZajednica(long zajednica) {
        this.zajednica = zajednica;
    }

    /*public long getFlair() {
        return flair;
    }

    public void setFlair(long flair) {
        this.flair = flair;
    }*/

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }
}
