package org.example.dictionaryee.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.dictionaryee.entity.Word;
import org.example.dictionaryee.service.api.DictionaryService;

@Path("/dictionary")
public class DictionaryResource {

    @EJB
    private DictionaryService dictionaryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Word getWords() {
        return dictionaryService.getWord();
    }
}
