package et.com.Lottery.rest;

import com.cassiomolin.security.exception.AuthenticationException;
import com.cassiomolin.user.domain.User;
import com.cassiomolin.user.service.UserService;
import et.com.Lottery.dto.requestData.RegisterUserIn;
import et.com.Lottery.dto.responseData.RegisterUserOut;
import et.com.Lottery.dto.responseData.UserListOut;
import et.com.Lottery.dto.restData.Status;
import et.com.Lottery.service.UserManagement;
import et.com.Lottery.utility.StatusInit;
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
@Path("/userManagement")
@Api("User Managemnet")
public class UserManagementEndPoint {

    @EJB
    UserManagement userManagement;
    @Context
    SecurityContext securityContext;

    @Path("addUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    @ApiOperation(value = "ADMIN", notes = "this will add user")
    public Status addUser(RegisterUserIn registerUserIn) {
        try {
            return this.userManagement.addUser(registerUserIn);
        } catch (Exception e) {
            throw new AuthenticationException("unknown error occurred while adding user");
        }
    }


    @Path("activateUser")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "{\"ADMIN\"}", notes = "activate user using parameter user <id>")
    @RolesAllowed({"ADMIN", "USER"})
    public Status activateUser(@QueryParam("id") Long id, @QueryParam("activate") Boolean activate) {
        try {
            return this.userManagement.userActivation(id, activate);
        } catch (Exception e) {
            throw new AuthenticationException("Unknown error occured while deactivating user");
        }
    }

    @Path("userList")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "ADMIN", notes = "this will list registed user")
    @RolesAllowed({"ADMIN", "USER"})
    public UserListOut userList(
            @QueryParam("start") Integer start,
            @QueryParam("max") Integer max) {
        return this.userManagement.userList(start, max);
    }

    @Path("searchUser")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "ADMIN", notes = "this will list registed user")
    @RolesAllowed({"ADMIN", "USER"})
    public UserListOut searchUser(@QueryParam("parameter") String parameter,
                                  @QueryParam("start") Integer start,
                                  @QueryParam("max") Integer max) {

        return this.userManagement.searchUser(parameter, start, max);

    }

}
