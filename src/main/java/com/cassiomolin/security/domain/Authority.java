package com.cassiomolin.security.domain;

import com.cassiomolin.user.domain.User;

/**
 * Represents an authority granted to a {@link User}.
 *
 * @author cassiomolin
 */
public enum Authority {
    ADMIN, USER, ACTUSER,CUSTOMER
}