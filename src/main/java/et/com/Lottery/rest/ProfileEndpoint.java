package et.com.Lottery.rest;

import com.cassiomolin.security.exception.AuthenticationException;
import com.cassiomolin.user.domain.User;
import com.cassiomolin.user.service.UserService;
import et.com.Lottery.dto.requestData.ChangePasswordIn;
import et.com.Lottery.dto.responseData.ProfileOut;
import et.com.Lottery.dto.restData.Status;
import et.com.Lottery.model.UsersData;
import et.com.Lottery.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@RequestScoped
@Path("profile")
@Api(value = "Profile")
public class ProfileEndpoint {
    @EJB
    ProfileService profile;
    @Context
    SecurityContext securityContext;
    @Inject
    UserService userService;

    @Path("viewProfile")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "PERMITED TO All", notes = "thi will show user Profile")
    @PermitAll
    public ProfileOut viewProfile(){
        try {
            Principal principal = this.securityContext.getUserPrincipal();
            User user = this.userService.findByUsernameOrEmail(principal.getName());
            return this.profile.viewProfile(user);
        } catch (Exception e) {
            throw new AuthenticationException("You should logged in fist.");
        }
    }
    @Path("editProfile")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value =  "Permited To All", notes = "this will edit profile data with the provided data")
    @PermitAll
    public Status editProfile(UsersData userData ){
        try {
            Principal principal = securityContext.getUserPrincipal();
            User user = this.userService.findByUsernameOrEmail(principal.getName());
            return profile.editProfile(userData, user);
        } catch (Exception e) {
            throw new AuthenticationException("You should logged in fist.");
        }
    }
    @Path("changePassword")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "PERMITED TO All", notes = "this will change user Password")
    @PermitAll
    public Status changePassword(ChangePasswordIn changePasswordIn){
        try {
            Principal principal = this.securityContext.getUserPrincipal();
            User user = this.userService.findByUsernameOrEmail(principal.getName());
            return this.profile.changePassword(changePasswordIn, user);
        } catch (Exception e) {
            throw new AuthenticationException("You should logged in fist.");
        }
    }
    @Path("resetPassword")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "ALLOWED TO ADMIN", notes = "RESETS PASSWORD GIVEN USER ID")
    @RolesAllowed("ADMIN")
    public Status resetPassword(@QueryParam("userId") Long userId) {
        return this.profile.reset(userId);
    }
}
