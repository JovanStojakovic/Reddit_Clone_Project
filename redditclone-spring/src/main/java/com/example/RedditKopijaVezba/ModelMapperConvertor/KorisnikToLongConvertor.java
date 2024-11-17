package com.example.RedditKopijaVezba.ModelMapperConvertor;

import com.example.RedditKopijaVezba.model.Korisnik;
import com.example.RedditKopijaVezba.model.Zajednica;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class KorisnikToLongConvertor implements Converter<Korisnik, Long> {

    @Override
    public Long convert(MappingContext<Korisnik, Long> context) {
        return context.getSource() == null ? null : context.getSource().getId();
    }
}
