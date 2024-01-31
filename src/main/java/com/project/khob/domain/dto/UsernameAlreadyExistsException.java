package com.project.khob.domain.dto;

import org.springframework.security.core.AuthenticationException;


public class UsernameAlreadyExistsException extends AuthenticationException {
    public UsernameAlreadyExistsException(String msg) {
        super(msg);
    }

    public UsernameAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
