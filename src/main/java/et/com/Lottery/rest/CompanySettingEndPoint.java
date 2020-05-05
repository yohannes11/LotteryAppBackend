package et.com.Lottery.rest;


import et.com.Lottery.dto.responseData.CompanySettingLisitOut;
import et.com.Lottery.dto.responseData.CompanySettingOut;
import et.com.Lottery.dto.restData.Status;
import et.com.Lottery.model.CompanySetting;
import et.com.Lottery.service.CompanySettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/companySetting")
@Api("company setting related")
@RequestScoped
public class CompanySettingEndPoint {

    @EJB
    CompanySettingService companySettingService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "", notes = "")
    @RolesAllowed({"ACTUSER"})
    @Path("/add")
    public CompanySettingOut addSetting(CompanySetting companySetting) {
        return companySettingService.addSetting(companySetting);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "", notes = "")
    @RolesAllowed({"ACTUSER"})
    @Path("/update")
    public CompanySettingOut updateSetting(CompanySetting companySetting) {
        return companySettingService.updateSetting(companySetting);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "", notes = "")
    @PermitAll
    @Path("/list")
    public CompanySettingLisitOut listCompanySetting(
            @QueryParam("start") Integer start,
            @QueryParam("max") Integer max) {
        return companySettingService.listCompanySetting(start, max);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "", notes = "")
    @RolesAllowed({"ACTUSER"})
    @Path("/delete")
    public Status deleteSetting(Long id) {
        return companySettingService.deleteSetting(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "", notes = "")
    @PermitAll
    @Path("/search")
    public CompanySettingOut findSetting(String name) {
        return companySettingService.searchSetting(name);
    }

}

