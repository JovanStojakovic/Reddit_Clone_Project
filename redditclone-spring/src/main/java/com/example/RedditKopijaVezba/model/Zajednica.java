package com.example.RedditKopijaVezba.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import java.time.LocalDate;

@Entity
public class Zajednica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private boolean suspendovana;
    private String razlogSuspenzije;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isSuspendovana() {
        return suspendovana;
    }

    public void setSuspendovana(boolean suspendovana) {
        this.suspendovana = suspendovana;
    }

    public String getRazlogSuspenzije() {
        return razlogSuspenzije;
    }

    public void setRazlogSuspenzije(String razlogSuspenzije) {
        this.razlogSuspenzije = razlogSuspenzije;
    }

}
