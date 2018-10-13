package com.pm.shared.exception;

import javax.ws.rs.BadRequestException;

public class BadModelRequestException extends BadRequestException {

    public BadModelRequestException() {
        super("Bad data model given");
    }
}
