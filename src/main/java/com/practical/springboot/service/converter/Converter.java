package com.practical.springboot.service.converter;

public interface Converter<S, T> {
    T convert(S source);
}
