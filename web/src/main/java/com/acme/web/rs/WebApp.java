package com.acme.web.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.acme.model.models.CreditApplication;
import com.acme.model.models.MortgageApplication;
import com.acme.service.exceps.CreditLimitException;
import com.acme.service.exceps.AgeOfMajorityException;
import com.acme.service.exceps.MinimumMortgageException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.acme.service.validate.InitiateCreditSubmission;
import com.acme.service.validate.InitiateMortgageSubmission;

@Path("/cc")
@Named
public class WebApp {

    @Inject
    private InitiateCreditSubmission beginCreditSubmission;

    @Inject
    private InitiateMortgageSubmission beginMortgageSubmission;
    
    @GET
    @Path("/applications/{a:credit|mortgage}")
    @Produces({"application/json"})
    public static Response getBadRequestResponse() {
        return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"UUID cannot be blank.\"}").build();
    }

    @GET
    @Path("/applications/credit/{id}")
    @Produces({"application/json"})
    public Response getCreditApp(
//            wadl - integrated with jaxrs and swagger - framework
            @PathParam("id") String id) throws Exception {
        try {
            if (beginCreditSubmission.getCreditApplication(id) == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Application not found for UUID: " + id + "\"}").build();
            } else {
                return Response.ok(beginCreditSubmission.getCreditApplication(id), MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception e) {
//            is/are there specific exception(s) to catch here?
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Path("/applications/mortgage/{id}")
    @Produces({"application/json"})
    public Response getMortgageApp(
            @PathParam("id") String id) throws Exception {
        try {
            if (beginMortgageSubmission.getMortgageApplication(id) == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Application not found for UUID: " + id + "\"}").build();
            } else {
                return Response.ok(beginMortgageSubmission.getMortgageApplication(id), MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("/applications/credit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewCard(CreditApplication creditApplication) throws Exception {
        try {
            try {
                return Response.ok(beginCreditSubmission.insertCreditApplication(creditApplication), MediaType.APPLICATION_JSON).build();
            } catch (CreditLimitException | AgeOfMajorityException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            } catch (ConstraintViolationException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"One or more fields were missing in the application.\"}").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("/applications/mortgage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewMortgage(MortgageApplication mortgageApplication) throws Exception {
        try {
            try {
                return Response.ok(beginMortgageSubmission.testMortgageBusinessLogic(mortgageApplication), MediaType.APPLICATION_JSON).build();
            } catch (MinimumMortgageException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            } catch (ConstraintViolationException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"One or more fields were missing in the application.\"}").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }
}
