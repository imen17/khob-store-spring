package com.project.khob.domain.dto;

import org.springframework.security.core.AuthenticationException;

public class UserDoesNotExistException extends AuthenticationException {

    private static final String reason = "User does not exist";
    public UserDoesNotExistException(Throwable cause) {
        super(reason, cause);
    }

    public UserDoesNotExistException() {
        super(reason);
    }
}
