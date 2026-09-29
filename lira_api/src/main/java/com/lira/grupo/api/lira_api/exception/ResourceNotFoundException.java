package com.lira.grupo.api.lira_api.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String recurso, Object identificador) {
        super(recurso + " não encontrado(a): " + identificador);
    }
}
