package com.cardanonft.api.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AesConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return AESUtil.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return AESUtil.decrypt(dbData);
    }
}
