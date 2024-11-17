package com.example.RedditKopijaVezba.ModelMapperConvertor;

import com.example.RedditKopijaVezba.model.Zajednica;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class ZajednicaToLongConverter implements Converter<Zajednica, Long> {
    @Override
    public Long convert(MappingContext<Zajednica, Long> context) {
        return context.getSource() == null ? null : context.getSource().getId();
    }
}
