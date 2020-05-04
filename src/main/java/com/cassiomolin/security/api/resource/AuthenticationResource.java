package com.cassiomolin.security.api.resource;

import com.cassiomolin.security.api.model.AuthenticationResponse;
import com.cassiomolin.security.api.model.AuthenticationToken;
import com.cassiomolin.security.api.model.UserCredentials;
import com.cassiomolin.security.exception.InvalidAuthenticationTokenException;
import com.cassiomolin.security.service.AuthenticationTokenService;
import com.cassiomolin.security.service.UsernamePasswordValidator;
import com.cassiomolin.user.domain.User;
import com.cassiomolin.user.service.UserService;
import et.com.Lottery.dto.restData.Token;
import et.com.Lottery.service.PasswordHistoryService;
import io.swagger.annotations.Api;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * JAX-RS resource class that provides operations for authentication.
 *
 * @author cassiomolin
 */
@RequestScoped
@Path("auth")
@Api(value = "authentication")
public class AuthenticationResource {
    private boolean roleAllowed;
    @Context
    private SecurityContext securityContext;

    @EJB
    PasswordHistoryService passwordHistoryService;
    @Inject
    private UsernamePasswordValidator usernamePasswordValidator;

    @Inject
    private AuthenticationTokenService authenticationTokenService;
    @Inject
    UserService userService;

    /**
     * Validate user credentials and issue a token for the user.
     *
     * @param credentials
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public AuthenticationResponse authenticate(UserCredentials credentials) {
        User user = usernamePasswordValidator.validateCredentials(credentials.getUsername(), credentials.getPassword());
        String token = authenticationTokenService.issueToken(user.getUsername(), user.getAuthorities());
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setToken(token);
        String authorities = user.getAuthorities().iterator().next().name();
        return new AuthenticationResponse(token, authorities, passwordHistoryService.isPasswordChanged(user));
    }

    /**
     * Refresh the authentication token for the current user.
     *
     * @return
     */
    @GET
    @Path("refresh")
    @Produces(MediaType.APPLICATION_JSON)
    public Token refreshToken() {
        User user = null;
        try {
            Principal principal = securityContext.getUserPrincipal();
            user = userService.findByUsernameOrEmail(principal.getName());
        } catch (Exception e) {
            throw new InvalidAuthenticationTokenException("Invalid Authentication token", e);
        }
        return new Token(authenticationTokenService.issueToken(user.getUsername(), user.getAuthorities()));
    }
}
