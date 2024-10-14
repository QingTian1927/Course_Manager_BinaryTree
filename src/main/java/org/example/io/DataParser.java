package org.example.io;

public interface DataParser<T> {
    String PROPERTY_SEPARATOR = "; ";
    T parse(String data);
}
