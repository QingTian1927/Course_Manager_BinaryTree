package org.example.io;

public interface DataWriter<T> {
    String PROPERTY_SEPARATOR = "; ";
    String write(T data);
}
