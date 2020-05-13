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
    @Path("/add")
    public Status addSetting(CompanySetting companySetting) {
        return companySettingService.addSetting(companySetting);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "", notes = "")
    @Path("/update")
    public Status updateSetting(CompanySetting companySetting) {
        System.out.println("===>"+companySetting);
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

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "", notes = "")
    @Path("/delete")
    public Status deleteSetting(@QueryParam("id") Long id) {
        return companySettingService.deleteSetting(id);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "", notes = "")
    @PermitAll
    @Path("/search")
    public CompanySettingOut findSetting(@QueryParam("name") String name) {
        return companySettingService.searchSetting(name);
    }

}

