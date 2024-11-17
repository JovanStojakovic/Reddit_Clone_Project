package com.example.RedditKopijaVezba.model;


import javax.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
//sad cemo da kazemo da ova nasa tabela moze da se prosiruje, znaci necemo imati posabne tabele za
//administratora nego cemo u ovu tabelu da dodamo kolonu jos jednu u kojoj ce da bude opisano koji korisnik je sta
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String email;
    private String avatar;
    private LocalDate registrationDate;
    private String description;
    private String displayName;

    @OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL)
    private List<Moderator> zajedniceKorisnika;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Moderator> getZajedniceKorisnika() {
        return zajedniceKorisnika;
    }

    public void setZajedniceKorisnika(List<Moderator> moderiraneZajednice) {
        this.zajedniceKorisnika = moderiraneZajednice;
    }
}
