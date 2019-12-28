package com.buffer.commerce.config;

public interface Action {
    abstract DTO validateParameter(final Parameter parameter);
    abstract DTO execute(final Parameter parameter);
}
