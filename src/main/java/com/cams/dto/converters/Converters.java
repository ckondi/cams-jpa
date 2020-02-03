package com.cams.dto.converters;

public interface Converters<T, R> {

    T convertDTOToDomain(R dtoObject);

}
