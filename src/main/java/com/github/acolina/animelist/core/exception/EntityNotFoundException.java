package com.github.acolina.animelist.core.exception;

public class EntityNotFoundException extends javax.persistence.EntityNotFoundException {
    public EntityNotFoundException(Long id) {
        super(String.format("The entity with id : %d was not found", id));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public static EntityNotFoundException createInstance(Long id) {
        return new EntityNotFoundException(id);
    }
}
