package com.pm.shared.exception;

import javax.ws.rs.NotFoundException;

public class EntityNotFoundException extends NotFoundException {
    public EntityNotFoundException() {
        super("Entity not found");
    }
}
