package org.example.dictionaryee.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dictionaryee.service.api.DictionaryService;

import javax.inject.Inject;

@Path("/dictionary")
public class DictionaryResource {

    @Inject
    private DictionaryService dictionaryService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getWords() throws JsonProcessingException {
        return Response.ok(dictionaryService.getWord()).build();
    }
}
