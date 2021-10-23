package com.begenerous.mapper;

import java.util.List;

public interface Mapper<From, To> {

    To convertOne(From from);

    List<To> convertList(List<From> from);

}