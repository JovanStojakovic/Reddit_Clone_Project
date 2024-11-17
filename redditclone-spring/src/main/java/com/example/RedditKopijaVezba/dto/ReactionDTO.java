package com.example.RedditKopijaVezba.dto;

import com.example.RedditKopijaVezba.model.EReactionType;

import java.time.LocalDate;

public class ReactionDTO {
    private long id;
    private EReactionType tipReakcije;
    private LocalDate createReactionDate;




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
}
