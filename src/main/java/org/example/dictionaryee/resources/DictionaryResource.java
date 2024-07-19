package org.example.dictionaryee.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dictionaryee.dto.WordDto;
import org.example.dictionaryee.entity.DictionaryType;
import org.example.dictionaryee.service.api.DictionaryService;

@Path("/dictionary")
public class DictionaryResource {

    @EJB
    private DictionaryService dictionaryService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getWords(@QueryParam("type") DictionaryType type) {
        return Response.ok(dictionaryService.findWords(type)).build();
    }

    @GET
    @Path("/translation")
    @Produces(MediaType.APPLICATION_XML)
    public Response getTranslation(@QueryParam("value") String value) {
        return Response.ok(dictionaryService.findTranslation(value)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createWord(WordDto dto) {
        dictionaryService.createWord(dto);
        return Response.status(201).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTranslation(WordDto dto) {
        dictionaryService.updateWord(dto);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteWord(WordDto dto) {
        dictionaryService.deleteWord(dto);
        return Response.status(204).build();
    }
}
